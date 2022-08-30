package com.haltebogen.gittalk.controller.chat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.*;
import com.haltebogen.gittalk.response.ResponseHandler;
import com.haltebogen.gittalk.service.chat.ChatService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @Operation(summary = "채팅방 생성", description = "채팅방 정보를 입력하면, 채팅방이 생성된다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping("/register")
    public ResponseEntity<Object> createRoom(@RequestBody ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {

        ChatRoomResponseDto chatRoomResponseDto = chatService.createChatRoom(chatRoomRegisterDto);
        return new ResponseHandler().generateResponse("OK",HttpStatus.OK, chatRoomResponseDto);
    }

    @Operation(summary = "채팅방 떠나기", description = "채팅방 떠나기를 누르면, 채팅방이 나가진다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping("/chatroom/left/")
    public ResponseEntity<Object> leftChatRoom(
            @RequestBody ChatRoomLeftDto chatRoomLeftDto,
            Principal principal
            ) throws JsonProcessingException{
        String leftUserId = principal.getName();
        ChatRoomResponseDto chatRoomResponseDto = chatService.leftChatRoom(Long.valueOf(leftUserId), chatRoomLeftDto);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }

    @Operation(summary = "채팅방 업데이트", description = "채팅방에 메세지가 입력되면, 메세지를 업데이트 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PatchMapping("/chatroom/{chatRoomId}")
    public ResponseEntity<Object> updateMessageInChatRoom(
            @RequestBody ChatMessageRequestDto chatMessageRequestDto,
            @PathVariable String chatRoomId
            ) throws JsonProcessingException {
        ChatRoomResponseDto chatRoomResponseDto = chatService.updateChatRoomMessages(chatRoomId, chatMessageRequestDto);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }

    @Operation(summary = "채팅방 초대기능", description = "초대할 팔로워의 닉네임을 입력하면 채팅방에 새로 유저를 초대한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping("/chatroom/{chatRoomId}/invitation")
    public ResponseEntity<Object> inviteMember(
            @RequestBody MemberInviteRequestDto inviteRequestDto,
            @PathVariable String chatRoomId
            ) throws JsonProcessingException {
        ChatRoomResponseDto chatRoomResponseDto = chatService.inviteMember(chatRoomId, inviteRequestDto);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }

    @Operation(summary = "채팅방 조회", description = "특정 채팅방을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping("/chatroom/detail/{chatRoomId}")
    public ResponseEntity<Object> getDetailChatRoom(
            @PathVariable String chatRoomId
    ) throws JsonProcessingException{
        ChatRoomResponseDto chatRoomResponseDto = chatService.getDetailChatRoom(chatRoomId);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }

    @Operation(summary = "전체 채팅방 조회", description = "전체 채팅방을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK!"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping("/chatroom}")
    public ResponseEntity<Object> getAllChatRoom(
            Principal principal
    ) throws JsonProcessingException {
        String memberId = principal.getName();
        List<ChatRoomResponseDto> chatRoomResponseDtoList = chatService.getAllChatRoom(memberId);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDtoList);
    }

}
