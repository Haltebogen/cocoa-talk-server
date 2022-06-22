package com.haltebogen.gittalk.service;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.init.InitInstance;
import com.haltebogen.gittalk.repository.MemberRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

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
            GithubUserResponseDto githubUserResponseDto = initMember.createGithubUserResponseDto();
            Member member = memberService.createMember(githubUserResponseDto);

            assertThat(githubUserResponseDto.getBio()).isEqualTo(member.getBio());
            assertThat(githubUserResponseDto.getName()).isEqualTo(member.getName());
            assertThat(githubUserResponseDto.getLogin()).isEqualTo(member.getNickName());
        }
    }

}
