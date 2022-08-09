package com.haltebogen.gittalk.service.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.ChatRoomRegisterDto;
import com.haltebogen.gittalk.entity.chat.ChatMessage;
import com.haltebogen.gittalk.entity.chat.ChatRoom;
import com.haltebogen.gittalk.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public void createChatRoom(ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {

        ChatRoom chatRoom = ChatRoom.builder()
                ._id(chatRoomRegisterDto.get_id())
                .roomName(chatRoomRegisterDto.getRoomName())
                .participantsId(chatRoomRegisterDto.getParticipantsId());

        chatRoomRepository.save(chatRoom);
    }

}
