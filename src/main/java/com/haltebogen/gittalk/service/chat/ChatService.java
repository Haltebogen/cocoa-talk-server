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

    @Transactional
    public ChatRoom leftChatRoom(String userId, ChatRoomLeftDto chatRoomLeftDto) throws JsonGenerationException {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomLeftDto.getChatRoomId()).get();
        Member leftUser = memberRepository.findById(Long.valueOf(userId)).get();

        chatRoom.getParticipantsId().remove(leftUser.getId());
        List<String> otherUser = chatRoom.getParticipantsId();
        ChatMessage leftChatMessage = new ChatMessage(
                "_id",
                "CHAT_MANAGER",
                otherUser,
                chatRoom.get_id(),
                leftUser.getName() + "님이 채팅방에서 나가셨습니다.",
                MessageStatus.TEXT,
                LocalDateTime.now(),
                MessageAlertType.LEFT
        );

        chatRoom.getMessages().add(leftChatMessage);

        return chatRoom;
    }

}
