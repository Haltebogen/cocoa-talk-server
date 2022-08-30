package com.haltebogen.gittalk.service.chat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.haltebogen.gittalk.dto.chat.*;
import com.haltebogen.gittalk.entity.chat.*;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.repository.chat.ChatMessageRepository;
import com.haltebogen.gittalk.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final String CHATTING_MANAGER = "관리자";

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatRoomResponseDto createChatRoom(ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {
        String chatRoomId = UUID.randomUUID().toString();

        if (isExistChatRoom(chatRoomRegisterDto)){
            ChatRoom chatRoom = chatRoomRepository.findByChatRoomId(chatRoomRegisterDto.getParticipantsId());
            return new ChatRoomResponseDto(chatRoom);
        }
        else {
            List<ChatMessage> messageList = new ArrayList<>();
            ChatRoom chatRoom = new ChatRoom(
                    chatRoomId,
                    chatRoomRegisterDto.getRoomName(),
                    messageList,
                    chatRoomRegisterDto.getParticipantsId(),
                    LocalDateTime.now()
            );
            chatRoomRepository.save(chatRoom);
            return new ChatRoomResponseDto(chatRoom);
        }

    }
    @Transactional
    public ChatRoomResponseDto createChatRoomBySearch(ChatRoomRegisterDto chatRoomRegisterDto) throws JsonProcessingException {
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
        return new ChatRoomResponseDto(chatRoom);
    }


    @Transactional
    public ChatRoomResponseDto leftChatRoom(Long leftUserId, ChatRoomLeftDto chatRoomLeftDto) throws JsonGenerationException {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomLeftDto.getChatRoomId()).get();
        Member leftUser = memberRepository.findById(leftUserId).get();

        chatRoom.getParticipantsId().remove(leftUser.getId());
        List<Long> otherUser = chatRoom.getParticipantsId();
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

        chatRoomRepository.save(chatRoom);

        return new ChatRoomResponseDto(chatRoom);

    }

    @Transactional
    public ChatRoomResponseDto updateChatRoomMessages(String chatRoomId, ChatMessageRequestDto chatMessageRequestDto) throws JsonProcessingException {
        ChatMessage chatMessage =  ChatMessage.builder()
                .sender(chatMessageRequestDto.getSender())
                .receiver(chatMessageRequestDto.getReceiver())
                .chatRoomId(chatRoomId)
                .message(chatMessageRequestDto.getMessage())
                .messageStatus(chatMessageRequestDto.getMessageStatus())
                .createdAt(LocalDateTime.now())
                .build();

        chatMessageRepository.save(chatMessage);

       ChatRoom chatRoom = chatRoomRepository.findById(chatMessage.getChatRoomId()).get();
       chatRoom.getMessages().add(chatMessage);
       chatRoomRepository.save(chatRoom);

       return new ChatRoomResponseDto(chatRoom);

    }

    @Transactional
    public ChatRoomResponseDto inviteMember(String chatRoomId, MemberInviteRequestDto memberInviteRequestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();

        String inviteUserNickName = memberInviteRequestDto.getNickname();
        Member invitedMember = memberRepository.findByNickName(inviteUserNickName).get();

        if (!chatRoom.getParticipantsId().contains(inviteUserNickName)) {
            chatRoom.getParticipantsId().add(invitedMember.getId());

            ChatMessage message = ChatMessage.builder()
                    .chatRoomId(chatRoom.get_id())
                    .sender(CHATTING_MANAGER)
                    .receiver(chatRoom.getParticipantsId())
                    .message(inviteUserNickName + "님이 초대되셨습니다!")
                    .messageStatus(MessageStatus.TEXT)
                    .createdAt(LocalDateTime.now())
                    .build();

            chatRoom.getMessages().add(message);

            chatRoomRepository.save(chatRoom);

            return new ChatRoomResponseDto(chatRoom);
        }
        ChatMessage message = ChatMessage.builder()
                .chatRoomId(chatRoom.get_id())
                .sender(CHATTING_MANAGER)
                .receiver(chatRoom.getParticipantsId())
                .message(inviteUserNickName + "님이 이미 채팅방에 존재합니다.")
                .messageStatus(MessageStatus.TEXT)
                .createdAt(LocalDateTime.now())
                .build();

        chatRoom.getMessages().add(message);
        chatRoomRepository.save(chatRoom);

        return new ChatRoomResponseDto(chatRoom);

    }

    @Transactional
    public ChatRoomResponseDto getDetailChatRoom(String chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();
        return new ChatRoomResponseDto(chatRoom);
    }

    @Transactional
    public List<ChatRoomResponseDto> getAllChatRoom(String userId) {
        List<ChatRoom> chatRoomList =  chatRoomRepository.findAll();

        List<ChatRoomResponseDto> chatRoomResponseDtoList = new ArrayList<>();

        for (int i=0; i <= chatRoomList.size(); i++) {
            if (chatRoomList.get(i).getParticipantsId().contains(userId)) {
                chatRoomResponseDtoList.add(new ChatRoomResponseDto(chatRoomList.get(i)));
            }
            }
        return chatRoomResponseDtoList;
    }

    private Boolean isExistChatRoom(ChatRoomRegisterDto chatRoomRegisterDto) {
        List<ChatRoom> chatRoomList = chatRoomRepository.findAll();
        for (int i=0; i<chatRoomList.size(); i++) {
            if(chatRoomList.get(i).getParticipantsId().equals(chatRoomRegisterDto.getParticipantsId())){
                return true;
            }
        }
        return false;
    }


}
