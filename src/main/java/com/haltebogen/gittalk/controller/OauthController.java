package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OauthController {

    private final Environment env;
//    private final RestTemplate restTemplate;

    @Deprecated
    @RequestMapping("/github")
    public String getGithubView(Model model) {
        model.addAttribute("clientId", env.getProperty("github.id"));
        model.addAttribute("clientSecret", env.getProperty("github.secret"));
        return "github";
    }

    @Deprecated
    @RequestMapping("/auth/github/callback")
    public String getCallbackView(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        params.add("client_id", env.getProperty("github.id"));
        params.add("client_secret", env.getProperty("github.secret"));
        params.add("code", code);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<TokenDto> response = restTemplate.postForEntity("https://github.com/login/oauth/access_token", request, TokenDto.class);

        HttpEntity requestGithubData = new HttpEntity(headers);
        headers.add("Authorization", "token " + response.getBody().getAccess_token());
        String URL_PATH = "https://api.github.com/user";
        ResponseEntity<String> responseGithubData = restTemplate.exchange(
                URL_PATH,
                HttpMethod.GET,
                requestGithubData,
                String.class
        );

        log.info("{}", responseGithubData.getBody());
        return "callback";
    }


}
