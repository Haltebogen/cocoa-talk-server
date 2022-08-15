package com.haltebogen.gittalk.dto.chat;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberInviteRequestDto {

    private String nickname;

    @Builder
    public MemberInviteRequestDto(String nickname) {
        this.nickname = nickname;
    }
}
