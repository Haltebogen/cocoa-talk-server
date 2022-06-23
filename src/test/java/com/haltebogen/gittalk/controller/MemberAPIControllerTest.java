package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.init.InitInstance;
import com.haltebogen.gittalk.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberAPIControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    private MemberRepository memberRepository;

    InitInstance initInstance = new InitInstance();

    @Nested
    @DisplayName("멤버 생성 테스트")
    class TestSearchMember {
        @Test
        @Transactional
        @DisplayName("이름으로 멤버 검색이 된다. - 결과가 있을 때")
        public void test_search_member_exist_성공() throws Exception {
            //given
            Member member = initInstance.createDefaultMember();
            String response = initInstance.createPaginationResponse(1, false);
            memberRepository.save(member);
            String keyword = "git-talk-admin";

            mvc.perform(get(String.format("/member/search?keyword=%s", keyword)))
                    .andExpect(status().isOk())
                    .andExpect(content().json(response));
        }

        @Test
        @Transactional
        @DisplayName("이름으로 멤버 검색이 된다. - 결과가 없을 때")
        public void test_search_member_not_exist_성공() throws Exception {
            Member member = initInstance.createDefaultMember();

            memberRepository.save(member);
            String keyword = "oereo";

            mvc.perform(get(String.format("/member/search?keyword=%s", keyword)))
                    .andExpect(status().isOk())
                    .andExpect(content().json("{}"));

        }

        @Test
        @Transactional
        @DisplayName("이름으로 멤버 검색이 2개 이상 존재한다. - 결과가 있을 때")
        public void test_search_member_exist_more_page_성공() throws Exception {
            Member member1 = initInstance.createCustomMember(1L, "git-talk-admin");
            Member member2 = initInstance.createCustomMember(2L, "git-talk");
            String response = initInstance.createPaginationResponse(2, true);
            memberRepository.save(member1);
            memberRepository.save(member2);

            String keyword = "git-talk";

            mvc.perform(get(String.format("/member/search?keyword=%s&size=1", keyword)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.totalPage").value(2))
                    .andExpect(jsonPath("$.data.hasNext").value(true));


        }
    }
}
