package com.haltebogen.gittalk.controller.chat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.ChatRoomLeftDto;
import com.haltebogen.gittalk.dto.chat.ChatRoomRegisterDto;
import com.haltebogen.gittalk.entity.chat.ChatRoom;
import com.haltebogen.gittalk.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/register")
    public ResponseEntity<?> createRoom(@RequestBody ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {
        chatService.createChatRoom(chatRoomRegisterDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{userName}/chatroom/left/")
    public ResponseEntity<ChatRoom> leftChatRoom(
            @RequestBody ChatRoomLeftDto chatRoomLeftDto,
            @PathVariable String userName) throws JsonProcessingException{
        ChatRoom chatRoom = chatService.leftChatRoom(userName, chatRoomLeftDto);

        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }


}
