package com.haltebogen.gittalk.service;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.ProviderType;
import com.haltebogen.gittalk.init.InitInstance;
import com.haltebogen.gittalk.repository.MemberRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;
    InitInstance initMember = new InitInstance();

    @Nested
    @DisplayName("Github User Data 기반으로 한 Member 객체 테스트")
    class TestJoinGithubMember {

        @Test
        @Transactional
        @DisplayName("Github User API 데이터를 기반으로 멤버 생성이 성공한다.")
        public void test_join_member_based_github_api_성공() {
            GithubUserResponseDto githubUserResponseDto = initMember.createGithubUserResponseDto(1234L);
            Member member = memberService.createMember(githubUserResponseDto);

            assertThat(githubUserResponseDto.getBio()).isEqualTo(member.getBio());
            assertThat(githubUserResponseDto.getAvatar_url()).isEqualTo(member.getProfileImageUrl());
            assertThat(githubUserResponseDto.getName()).isEqualTo(member.getName());
            assertThat(githubUserResponseDto.getLogin()).isEqualTo(member.getNickName());
            assertThat(githubUserResponseDto.getCompany()).isEqualTo(member.getCompany());
            assertThat(githubUserResponseDto.getFollowers()).isEqualTo(member.getFollowersNum());
            assertThat(githubUserResponseDto.getFollowings()).isEqualTo(member.getFollowingsNum());
            assertThat(githubUserResponseDto.getFollowers_url()).isEqualTo(member.getFollowersUrl());
            assertThat(githubUserResponseDto.getFollowings_url()).isEqualTo(member.getFollowingUrl());
            assertThat(ProviderType.GITHUB).isEqualTo(member.getProviderType());
            assertThat(githubUserResponseDto.getId()).isEqualTo(member.getProviderId());
        }

        @Test
        @Transactional
        @DisplayName("기존 github login 으로 가입된 유저가 있으면 Member 객체가 중복 생생이 되지 않는다")
        public void test_not_join_duplicated_member_성공() {
            GithubUserResponseDto firstGithubUserResponseDto = initMember.createGithubUserResponseDto(1234L);
            GithubUserResponseDto secondGithubUserResponseDto = initMember.createGithubUserResponseDto(1234L);

            memberService.createMember(firstGithubUserResponseDto);
            memberService.createMember(secondGithubUserResponseDto);

            List<Member> member = memberRepository.findAllByProviderId(1234L);
            assertThat(member.size()).isEqualTo(1);
        }
    }

}
