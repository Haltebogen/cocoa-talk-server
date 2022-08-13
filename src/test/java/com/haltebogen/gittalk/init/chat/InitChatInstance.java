package com.haltebogen.gittalk.init.chat;

import com.haltebogen.gittalk.dto.chat.ChatRoomLeftDto;
import com.haltebogen.gittalk.dto.chat.ChatRoomRegisterDto;
import com.haltebogen.gittalk.entity.chat.ChatRoom;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class InitChatInstance {

    private final String CHAT_ROOM_ID = "1";

    private final String ROOM_NAME = "신나는 채팅방";

    private final Long USER1 = 1L;

    private final Long USER2 = 2L;

    private final List<Long> PARTICIPANTS_ID = new ArrayList<>(Arrays.asList(USER1,USER2));


    public ChatRoomRegisterDto createChatRoomRegisterDto() {
        return ChatRoomRegisterDto.builder()
                        .roomName(ROOM_NAME)
                                .participantsId(PARTICIPANTS_ID).build();
    }

    public ChatRoomLeftDto createChatRoomLeftDto() {
        ChatRoomLeftDto chatRoomLeftDto =  new ChatRoomLeftDto();
        chatRoomLeftDto.setChatRoomId(CHAT_ROOM_ID);
        return chatRoomLeftDto;
    }


}
