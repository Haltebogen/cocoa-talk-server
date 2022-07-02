package com.haltebogen.gittalk.service;

import com.haltebogen.gittalk.dto.member.MemberResponseDto;
import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.ProviderType;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.trace.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Trace
    public Page<Member> findUserBySearch(Pageable pageable, String keyword) {
        return memberRepository.findBySearch(keyword, pageable);
    }

    public Member createMember(GithubUserResponseDto githubUserResponseDto) {
        if (!isExistMember(githubUserResponseDto)) {
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
                    .followingsNum(githubUserResponseDto.getFollowings()).build();
            memberRepository.save(member);
            return member;
        }

        return memberRepository.findByProviderId(githubUserResponseDto.getId()).get();

    }

    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    private boolean isExistMember(GithubUserResponseDto githubUserResponseDto){
        Long githubUserId = githubUserResponseDto.getId();

        return memberRepository.findByProviderId(githubUserId).isPresent();
    }

}
