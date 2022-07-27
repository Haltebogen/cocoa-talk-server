package com.haltebogen.gittalk.controller;

import com.haltebogen.gittalk.dto.PaginationResponseDto;
import com.haltebogen.gittalk.dto.member.*;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.response.ResponseHandler;
import com.haltebogen.gittalk.service.user.FollowService;
import com.haltebogen.gittalk.service.user.MemberService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberAPIController {
    private final MemberService memberService;
    private final FollowService followService;

    @Deprecated
    @Operation(summary="멤버 검색", description = "키워드를 이용해서, 멤버를 검색할 수 있다.")
    @ApiResponses({
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=500, message = "Server Error")
    })
    @GetMapping("/search")
    public ResponseEntity<Object> searchMember(
            @PageableDefault Pageable pageable,
            @RequestParam String keyword
    ) {
        Page<Member> pageData = memberService.findUserBySearch(pageable, keyword);
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, new PaginationResponseDto(
                pageData.getTotalPages(),
                pageData.hasNext(),
                pageData.stream().map(MemberResponseDto::new).collect(Collectors.toList())
        ));
    }

    @GetMapping
    public ResponseEntity<Object> getProfile(Principal principal) {
        String memberId = principal.getName();
        MemberDetailResponseDto memberResponseDto = memberService.getMember(Long.valueOf(memberId));
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, memberResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOtherProfile(@PathVariable Long id) {
        MemberDetailResponseDto memberResponseDto = memberService.getMember(id);
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, memberResponseDto);
    }

    @PostMapping("/follow")
    public ResponseEntity<Object> createFollowRequest(
            Principal principal,
            @RequestBody FollowRequestDto followRequestDto
    ) {
        String memberId = principal.getName();
        FollowResponseDto followResponseDto = followService.createFollowRequest(
                Long.valueOf(memberId),
                followRequestDto.getFollowing()
        );
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, followResponseDto);
    }
    @PostMapping("/follow/allow")
    public ResponseEntity<Object> createFollowAllow(
            Principal principal,
            @RequestBody FollowRequestDto followRequestDto
    ) {
        String memberId = principal.getName();
        FollowResponseDto followResponseDto = followService.createFollowAllow(
                Long.valueOf(memberId),
                followRequestDto.getFollowing()
        );
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, followResponseDto);
    }

    @GetMapping("/follows")
    public ResponseEntity<Object> getFollows(Principal principal) {
        String memberId = principal.getName();
        List<MemberDetailResponseDto> followers = followService.getFollowers(Long.valueOf(memberId));
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, followers);
    }

    @Operation(summary="팔로우 요청 멤버 검색", description = "키워드를 이용해서, 팔로우 요청을 보낼 멤버를 검색할 수 있다.")
    @ApiResponses({
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=400, message = "keyword parameter 가 존재하지 않습니다."),
            @ApiResponse(code=500, message = "Server Error")
    })
    @GetMapping("/follow/search")
    public ResponseEntity<Object> searchFollow(
            Principal principal,
            @PageableDefault Pageable pageable,
            @RequestParam String keyword
    ) {
        String memberId = principal.getName();
        List<SearchGithubFollowResponseDto> follows = memberService.findGithubFollowBySearch(Long.valueOf(memberId), pageable, keyword);
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, follows);
    }

    @Deprecated
    @GetMapping("/profiles")
    public ResponseEntity<Object> getChatMembers(
            @PageableDefault Pageable pageable,
            Principal principal
    ) {
        String memberId = principal.getName();
        return ResponseHandler.generateResponse("ok", HttpStatus.OK, "");
    }
}
