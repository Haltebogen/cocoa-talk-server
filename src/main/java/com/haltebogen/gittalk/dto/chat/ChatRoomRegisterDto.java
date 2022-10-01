package com.haltebogen.gittalk.dto.chat;

import com.haltebogen.gittalk.dto.member.ChatMemberRequestDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
@Getter
@NoArgsConstructor
public class ChatRoomRegisterDto {
    private String roomName;
    private List<Long> participantsId;

    @Builder
    public ChatRoomRegisterDto(String roomName, List<Long> participantsId) {
        this.roomName = roomName;
        this.participantsId = participantsId;
    }
}
