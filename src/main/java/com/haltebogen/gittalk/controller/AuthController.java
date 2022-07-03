package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.dto.oauth.JwtTokenDto;
import com.haltebogen.gittalk.dto.oauth.TokenDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.response.ResponseHandler;
import com.haltebogen.gittalk.service.MemberService;
import com.haltebogen.gittalk.service.OAuthService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final MemberService memberService;
    private final OAuthService oAuthService;


    @ApiResponses({
            @ApiResponse(code=200, message = "OK!"),
            @ApiResponse(code=400, message = "Bad Request!"),
            @ApiResponse(code=500, message = "Server Error")
    })
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody TokenDto tokenDto) {
        GithubUserResponseDto githubUserResponseDto = oAuthService.getGithubUserData(tokenDto);
        Member member = memberService.createMember(githubUserResponseDto);
        JwtTokenDto jwtTokenDto = oAuthService.createLoginMemberJwt(member);
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, jwtTokenDto);
    }
}
