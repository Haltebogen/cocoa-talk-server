package com.haltebogen.gittalk.service.chat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.ChatRoomLeftDto;
import com.haltebogen.gittalk.dto.chat.ChatRoomRegisterDto;
import com.haltebogen.gittalk.entity.chat.ChatMessage;
import com.haltebogen.gittalk.entity.chat.ChatRoom;
import com.haltebogen.gittalk.entity.chat.MessageAlertType;
import com.haltebogen.gittalk.entity.chat.MessageStatus;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createChatRoom(ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {
        String chatRoomId = UUID.randomUUID().toString();
        List<ChatMessage> messageList = new ArrayList<>();


        ChatRoom chatRoom = new ChatRoom(
                chatRoomId,
                chatRoomRegisterDto.getRoomName(),
                messageList,
                chatRoomRegisterDto.getParticipantsId(),
                LocalDateTime.now()
        );

        chatRoomRepository.save(chatRoom);
    }

}
