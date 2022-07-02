package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.Member;
import lombok.Getter;

@Getter
public class ChatMemberResponseDto {

    private String email;
    private String nickName;
    private String profileImageUrl;
    private String statusMessage;
    private Boolean isFollower;
    private Boolean isFollowing;
    private Boolean isMember;

    public ChatMemberResponseDto(Member member, Boolean isFollower, Boolean isFollowing, Boolean isMember) {
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
        this.statusMessage = member.getStatusMessage();
        this.isFollower = isFollower;
        this.isFollowing = isFollowing;
        this.isMember = isMember;
    }
}
