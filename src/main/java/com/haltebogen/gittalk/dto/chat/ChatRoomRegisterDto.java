package com.haltebogen.gittalk.dto.chat;

import com.haltebogen.gittalk.dto.member.ChatMemberRequestDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ChatRoomRegisterDto {
    private String roomName;
    private List<Long> participantsId;

    @Builder
    public ChatRoomRegisterDto(String roomName, List<Long> participantsId) {
        this.roomName = roomName;
        this.participantsId = participantsId;
    }
}
