package com.haltebogen.gittalk.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class OauthServiceTest {

    @Autowired
    private Environment env;
    @Autowired
    MockMvc mvc;
    @Autowired
    private RestTemplate restTemplate;

    @Nested
    @DisplayName("Github login 을 통해서 github access token 테스트")
    class TestGetAccessTokenDto {
        @Test
        @Transactional
        @DisplayName("Github login 을 통해서 github access token 을 받아올 수 있다.")
        public void test_get_github_access_token_성공() throws Exception {
            String githubLoginUrl = String.format("https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=http://localhost:8080/auth/github/callback", env.getProperty("github.id"));
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(githubLoginUrl, String.class);
        }
    }
}
