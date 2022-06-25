package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.oauth.TokenDto;
import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.service.MemberService;
import com.haltebogen.gittalk.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Deprecated
@Controller
@Slf4j
@RequiredArgsConstructor
public class OauthController {

    private final Environment env;
    private final OAuthService oAuthService;
    private final MemberService memberService;

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
        TokenDto tokenDto = oAuthService.getAccessTokenDto(code);
        GithubUserResponseDto githubUserResponseDto = oAuthService.getGithubUserData(tokenDto);
        Member member = memberService.createMember(githubUserResponseDto);
        return "callback";
    }
}
