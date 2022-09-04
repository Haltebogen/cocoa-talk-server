package com.haltebogen.gittalk.service.user;

import com.haltebogen.gittalk.dto.member.GitUserProfileDto;
import com.haltebogen.gittalk.dto.member.MemberDetailRequestDto;
import com.haltebogen.gittalk.dto.member.MemberDetailResponseDto;
import com.haltebogen.gittalk.dto.member.SearchGithubFollowResponseDto;
import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.entity.user.ProviderType;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.trace.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;
    private final Environment env;

    private final String PAGINATION_PAGE_SIZE = "1000000";
    private final String GITHUB_FOLLOWERS_API_URL_PATH = "https://api.github.com/users/%s/followers?per_page=%s";
    private final String GITHUB_FOLLOWINGS_API_URL_PATH = "https://api.github.com/users/%s/following?per_page=%s";

    @Trace
    public Page<Member> findUserBySearch(Pageable pageable, String keyword) {
        return memberRepository.findBySearch(keyword, pageable);
    }

    public Member createMember(GithubUserResponseDto githubUserResponseDto) {
        if (!isExistMember(githubUserResponseDto.getId())) {
            Member member = Member.builder()
                    .providerId(githubUserResponseDto.getId())
                    .providerType(ProviderType.GITHUB)
                    .email("null")
                    .nickName(githubUserResponseDto.getLogin())
                    .name(githubUserResponseDto.getName())
                    .bio(githubUserResponseDto.getBio())
                    .company(githubUserResponseDto.getCompany())
                    .profileImageUrl(githubUserResponseDto.getAvatar_url())
                    .followersUrl(githubUserResponseDto.getFollowers_url())
                    .followingUrl(githubUserResponseDto.getFollowings_url())
                    .followersNum(githubUserResponseDto.getFollowers())
                    .followingsNum(githubUserResponseDto.getFollowing()).build();
            memberRepository.save(member);
            return member;
        }

        return memberRepository.findByProviderId(githubUserResponseDto.getId()).get();
    }

    public MemberDetailResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new MemberDetailResponseDto(member);
    }

    public MemberDetailResponseDto updateMember(Long id, MemberDetailRequestDto memberDetailRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        member.updateBio(memberDetailRequestDto.getBio());
        member.updateStatusMessage(memberDetailRequestDto.getStatusMessage());
        member.updateProfileImageUrl(member.getProfileImageUrl());
        memberRepository.save(member);
        return new MemberDetailResponseDto(member);
    }

    @Trace
    public List<SearchGithubFollowResponseDto> findGithubFollowBySearch(Long id, String keyword) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        List<GitUserProfileDto> followers = getGitUserFollowers(member.getNickName());
        List<GitUserProfileDto> followings = getGitUserFollowings(member.getNickName());
        List<SearchGithubFollowResponseDto> githubFollowsData = generateGithubFollows(followers, followings);
        List<SearchGithubFollowResponseDto> results = new ArrayList<>();
        for (SearchGithubFollowResponseDto searchGithubFollowResponseDto: githubFollowsData
             ) {
            if (searchGithubFollowResponseDto.getNickName().contains(keyword)) {
                results.add(searchGithubFollowResponseDto);
            }
        }
        return results;
    }

    private List<SearchGithubFollowResponseDto> generateGithubFollows(
            List<GitUserProfileDto> followers,
            List<GitUserProfileDto> followings
    ) {
        List<SearchGithubFollowResponseDto> githubFollows = new ArrayList<>();

        for (GitUserProfileDto follower : followers
        ) {
            SearchGithubFollowResponseDto searchGithubFollowResponseDto = SearchGithubFollowResponseDto.builder()
                    .member(follower)
                    .isFollower(true)
                    .isFollowing(false)
                    .isMember(false).build();
            if (isExistMember(follower.getId())) {
                searchGithubFollowResponseDto.updateIsMember(true);
            }
            githubFollows.add(searchGithubFollowResponseDto);
        }

        for (GitUserProfileDto following : followings
        ) {
            Boolean isFollowing = false;
            for (SearchGithubFollowResponseDto searchGithubFollowResponseDto : githubFollows) {
                if (searchGithubFollowResponseDto.getProviderId().equals(following.getId())) {
                    searchGithubFollowResponseDto.updateIsFollowing(true);
                    isFollowing = true;
                }
            }

            if (!isFollowing) {
                SearchGithubFollowResponseDto searchGithubFollowResponseDto = SearchGithubFollowResponseDto.builder()
                        .member(following)
                        .isFollower(false)
                        .isFollowing(true)
                        .isMember(false).build();
                if (isExistMember(following.getId())) {
                    searchGithubFollowResponseDto.updateIsMember(true);
                }
                githubFollows.add(searchGithubFollowResponseDto);
            }
        }
        return githubFollows;
    }

    private List<GitUserProfileDto> getGitUserFollowers(String nickName) {
        HttpHeaders headers = new HttpHeaders();
        String githubSecret = "token "+ env.getProperty("github.secret");
        headers.set("Authorization", githubSecret);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<GitUserProfileDto[]> responseGithubData = restTemplate.exchange(
                String.format(GITHUB_FOLLOWERS_API_URL_PATH, nickName, PAGINATION_PAGE_SIZE),
                HttpMethod.GET,
                httpEntity,
                GitUserProfileDto[].class
        );

        return Arrays.asList(responseGithubData.getBody());
    }

    private List<GitUserProfileDto> getGitUserFollowings(String nickName) {
        HttpHeaders headers = new HttpHeaders();
        String githubSecret = "token "+ env.getProperty("github.secret");
        headers.set("Authorization", githubSecret);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<GitUserProfileDto[]> responseGithubData = restTemplate.exchange(
                String.format(GITHUB_FOLLOWINGS_API_URL_PATH, nickName, PAGINATION_PAGE_SIZE),
                HttpMethod.GET,
                httpEntity,
                GitUserProfileDto[].class
        );

        return Arrays.asList(responseGithubData.getBody());
    }

    private boolean isExistMember(Long providerId) {
        return memberRepository.existsByProviderId(providerId);
    }
}
