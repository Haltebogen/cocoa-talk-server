package com.haltebogen.gittalk.service.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.ChatRoomRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    @Transactional
    public void createChatRoom(ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {
        chatRoomRegisterDto.getParticipantsId();
    }
}
