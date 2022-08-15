package com.haltebogen.gittalk.controller.chat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.*;
import com.haltebogen.gittalk.response.ResponseHandler;
import com.haltebogen.gittalk.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/register")
    public ResponseEntity<Object> createRoom(@RequestBody ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {

        ChatRoomResponseDto chatRoomResponseDto = chatService.createChatRoom(chatRoomRegisterDto);
        return new ResponseHandler().generateResponse("OK",HttpStatus.OK, chatRoomResponseDto);
    }

    @PostMapping("{leftUserId}/chatroom/left/")
    public ResponseEntity<Object> leftChatRoom(
            @RequestBody ChatRoomLeftDto chatRoomLeftDto,
            @PathVariable Long leftUserId) throws JsonProcessingException{

        ChatRoomResponseDto chatRoomResponseDto = chatService.leftChatRoom(leftUserId, chatRoomLeftDto);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }

    @PatchMapping("/chatroom/{chatRoomId}")
    public ResponseEntity<Object> updateMessage(
            @RequestBody ChatMessageRequestDto chatMessageRequestDto,
            @PathVariable String chatRoomId
            ) throws JsonProcessingException {
        ChatRoomResponseDto chatRoomResponseDto = chatService.updateChatRoomMessages(chatRoomId, chatMessageRequestDto);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }

    @PostMapping("/chatroom/{chatRoomId}/invitation")
    public ResponseEntity<Object> inviteMember(
            @RequestBody MemberInviteRequestDto inviteRequestDto,
            @PathVariable String chatRoomId
            ) throws JsonProcessingException {
        ChatRoomResponseDto chatRoomResponseDto = chatService.inviteMember(chatRoomId, inviteRequestDto);

        return new ResponseHandler().generateResponse("OK", HttpStatus.OK, chatRoomResponseDto);
    }



}
