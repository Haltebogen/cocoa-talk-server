//package com.haltebogen.gittalk.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.haltebogen.gittalk.dto.chat.ChatRoomLeftDto;
//import com.haltebogen.gittalk.dto.chat.ChatRoomRegisterDto;
//import com.haltebogen.gittalk.dto.chat.ChatRoomResponseDto;
//import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
//import com.haltebogen.gittalk.entity.chat.ChatMessage;
//import com.haltebogen.gittalk.entity.chat.ChatRoom;
//import com.haltebogen.gittalk.entity.user.Member;
//import com.haltebogen.gittalk.init.InitInstance;
//import com.haltebogen.gittalk.init.chat.InitChatInstance;
//import com.haltebogen.gittalk.repository.chat.ChatRoomRepository;
//import com.haltebogen.gittalk.service.chat.ChatService;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.haltebogen.gittalk.service.user.MemberService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@DisplayName("Chatting 가관련 API Test")
//public class ChatServiceTest {
//
//    @Autowired
//    private ChatService chatService;
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private ChatRoomRepository chatRoomRepository;
//
//    InitChatInstance chatInstance = new InitChatInstance();
//    InitInstance initInstance = new InitInstance();
//
//
//    @Test
//    @Transactional
//    @DisplayName("사용자가 채팅방 이름과 참가자를 선택하면 chatting 창이 생성된다.")
//    public void test_채팅방_생성_API_성공() {
//        ChatRoomRegisterDto chatRoomRegisterDto = chatInstance.createChatRoomRegisterDto();
//
//        try {
//            ChatRoomResponseDto chatRoomResponseDto = chatService.createChatRoom(chatRoomRegisterDto);
//
//            assertThat(chatRoomResponseDto.getRoomName()).isEqualTo(chatRoomRegisterDto.getRoomName());
//            assertThat(chatRoomResponseDto.getParticipantsId()).isEqualTo(chatRoomRegisterDto.getParticipantsId());
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("사용자가 채팅방 나가기를 하면 유저가 채팅방에서 나가진다.")
//    public void test_채팅방_떠나기_API_성공() {
//
//        ChatRoomLeftDto chatRoomLeftDto = chatInstance.createChatRoomLeftDto();
//        ChatRoomRegisterDto chatRoomRegisterDto = chatInstance.createChatRoomRegisterDto();
//        List<ChatMessage> messages = new ArrayList<>();
//
//        GithubUserResponseDto githubUserResponseDto = initInstance.createGithubUserResponseDto(1L, "test-left-user");
//        Member leftMember = memberService.createMember(githubUserResponseDto);
//
//        ChatRoom chatRoom = new ChatRoom(
//                chatRoomLeftDto.getChatRoomId(),
//                chatRoomRegisterDto.getRoomName(),
//                messages,
//                chatRoomRegisterDto.getParticipantsId(),
//                LocalDateTime.now()
//        );
//        chatRoomRepository.save(chatRoom);
//
//        List<Long> PARTICIPANTS_ID = chatRoom.getParticipantsId();
//        PARTICIPANTS_ID.remove(0);
//
//        try {
//            ChatRoomResponseDto chatRoomResponseDto = chatService.leftChatRoom(1L, chatRoomLeftDto);
//            assertThat(chatRoomResponseDto.getParticipantsId()).isEqualTo(PARTICIPANTS_ID);
//            assertThat(chatRoomResponseDto.getMessages().size()).isEqualTo(1);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//}
