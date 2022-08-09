package com.haltebogen.gittalk.dto.chat;

import com.haltebogen.gittalk.dto.member.ChatMemberRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class ChatRoomRegisterDto {
    private String _id;
    private String roomName;
    private List<String> participantsId;
}
