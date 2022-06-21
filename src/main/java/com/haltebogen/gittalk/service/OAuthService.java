package com.haltebogen.gittalk.service;

import com.haltebogen.gittalk.dto.TokenDto;
import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final String GITHUB_USER_API_URL_PATH = "https://api.github.com/user";

    private final MemberRepository memberRepository;
    private final Environment env;
    private final RestTemplate restTemplate;

    // Access Token 받는 로직
    @Deprecated
    public TokenDto getAccessTokenDto(String code) {

        MultiValueMap<String, String> clientSecretPair = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();

        clientSecretPair.add("client_id", env.getProperty("github.id"));
        clientSecretPair.add("client_secret", env.getProperty("github.secret"));
        clientSecretPair.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(clientSecretPair, headers);

        ResponseEntity<TokenDto> response = restTemplate.postForEntity("\"https://github.com/login/oauth/access_token", request, TokenDto.class);

        return response.getBody();
    }

    // Github 정보 받아오는 로직
    public GithubUserResponseDto getGithubUserData(TokenDto accessTokenDto) {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "token " + accessTokenDto.getAccess_token());

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<GithubUserResponseDto> responseGithubData = restTemplate.exchange(
                GITHUB_USER_API_URL_PATH,
                HttpMethod.GET,
                httpEntity,
                GithubUserResponseDto.class
        );

        return responseGithubData.getBody();
    }

}
