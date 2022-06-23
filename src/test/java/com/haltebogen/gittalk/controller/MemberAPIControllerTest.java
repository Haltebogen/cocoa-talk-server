package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.init.InitInstance;
import com.haltebogen.gittalk.repository.MemberRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    }
}
