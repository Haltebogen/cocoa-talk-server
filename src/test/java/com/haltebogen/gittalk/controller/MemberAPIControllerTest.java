package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.dto.oauth.JwtTokenDto;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.init.InitInstance;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.service.user.MemberService;
import com.haltebogen.gittalk.service.user.OAuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;


import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberAPIControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private MemberService memberService;

    InitInstance initInstance = new InitInstance();

    @Nested
    @DisplayName("멤버 생성 테스트")
    class TestSearchMember {
        @Test
        @Transactional
        @DisplayName("이름으로 멤버 검색이 된다. - 결과가 있을 때")
        public void test_search_member_exist_성공() throws Exception {
            //given
            GithubUserResponseDto githubUserResponseDto = initInstance.createGithubUserResponseDto(1L, "git-talk-admin");
            Member member = memberService.createMember(githubUserResponseDto);
            JwtTokenDto jwtTokenDto = oAuthService.createLoginMemberJwt(member);
            String accessToken = jwtTokenDto.getAccessToken();
            String response = initInstance.createPaginationResponse(1, false);
            String keyword = "git-talk-admin";

            MvcResult mvcResult = mvc.perform(get(String.format("/api/v1/member/search?keyword=%s", keyword)).header("Authorization", "Bearer " + accessToken)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andReturn();

            MockHttpServletResponse mockHttpServletResponse= mvcResult.getResponse();
            assertThat(mockHttpServletResponse.getContentAsString()).isEqualTo(response);
        }

        @Test
        @Transactional
        @DisplayName("이름으로 멤버 검색이 된다. - 결과가 없을 때")
        public void test_search_member_not_exist_성공() throws Exception {
            GithubUserResponseDto githubUserResponseDto = initInstance.createGithubUserResponseDto(1L, "git-talk-admin");
            Member member = memberService.createMember(githubUserResponseDto);
            JwtTokenDto jwtTokenDto = oAuthService.createLoginMemberJwt(member);
            String accessToken = jwtTokenDto.getAccessToken();
            String keyword = "oereo";

            mvc.perform(get(String.format("/api/v1/member/search?keyword=%s", keyword)).header("Authorization", "Bearer " + accessToken)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("{\"data\":{\"totalPage\":0,\"hasNext\":false,\"data\":[]},\"message\":\"ok\",\"status\":200}"));
        }

        @Test
        @Transactional
        @DisplayName("이름으로 멤버 검색이 2개 이상 존재한다. - 결과가 있을 때")
        public void test_search_member_exist_more_page_성공() throws Exception {
            GithubUserResponseDto githubUserResponseDto1 = initInstance.createGithubUserResponseDto(1L, "git-talk-admin");
            Member member1 = memberService.createMember(githubUserResponseDto1);
            GithubUserResponseDto githubUserResponseDto2 = initInstance.createGithubUserResponseDto(2L, "git-talk");
            Member member2 = memberService.createMember(githubUserResponseDto2);
            JwtTokenDto jwtTokenDto = oAuthService.createLoginMemberJwt(member1);
            String accessToken = jwtTokenDto.getAccessToken();


            String keyword = "git-talk";

            mvc.perform(get(String.format("/api/v1/member/search?keyword=%s&size=1", keyword)).header("Authorization", "Bearer " + accessToken)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.totalPage").value(2))
                    .andExpect(jsonPath("$.data.hasNext").value(true));
        }

        @Test
        @Transactional
        @DisplayName("keyword 가 없을 시에 멤버 검색이 실패한다. - 결과가 있을 때")
        public void test_search_member_exist_실패_BAD_REQUEST() throws Exception {
            //given
            GithubUserResponseDto githubUserResponseDto1 = initInstance.createGithubUserResponseDto(1L, "git-talk-admin");
            Member member = memberService.createMember(githubUserResponseDto1);
            JwtTokenDto jwtTokenDto = oAuthService.createLoginMemberJwt(member);
            String accessToken = jwtTokenDto.getAccessToken();
            String keyword = "git-talk-admin";

            mvc.perform(get(String.format("/api/v1/member/search", keyword)).header("Authorization", "Bearer " + accessToken)
                    .contentType("application/json"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @Transactional
        @DisplayName("header 안에 토큰이 없을 시 401 에러를 반환한다.")
        public void test_search_member_실패_401_error() throws Exception {
            //given
            GithubUserResponseDto githubUserResponseDto1 = initInstance.createGithubUserResponseDto(1L, "git-talk-admin");
            memberService.createMember(githubUserResponseDto1);
            String keyword = "git-talk-admin";

            mvc.perform(get(String.format("/api/v1/member/search", keyword))
                            .contentType("application/json"))
                    .andExpect(status().isUnauthorized());
        }
    }
}
