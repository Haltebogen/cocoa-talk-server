package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.user.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String email;
    private String nickName;
    private String profileImageUrl;
    private String statusMessage;
    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
        this.statusMessage = member.getStatusMessage();
    }
}
