package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.oauth.TokenDto;
import com.haltebogen.gittalk.service.user.MemberService;
import com.haltebogen.gittalk.service.user.OAuthService;
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
    @RequestMapping("/api/auth/github/callback")
    public String getCallbackView(String code) {
        TokenDto tokenDto = oAuthService.getAccessTokenDto(code);
        log.info("tokenDto: {}, {}, {}", tokenDto.getAccess_token(), tokenDto.getToken_type(), tokenDto.getScope());
        return "callback";
    }
}
