package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMemberResponseDto {

    private Long providerId;
    private String email;
    private String nickName;
    private String profileImageUrl;
    private String statusMessage;
    private Boolean isFollower;
    private Boolean isFollowing;
    private Boolean isMember;

    @Builder
    public ChatMemberResponseDto(GitUserProfileDto member, Boolean isFollower, Boolean isFollowing, Boolean isMember) {
        this.providerId = member.getId();
        this.email = member.getEmail();
        this.nickName = member.getLogin();
        this.profileImageUrl = member.getAvatar_url();
        this.statusMessage = member.getBio();
        this.isFollower = isFollower;
        this.isFollowing = isFollowing;
        this.isMember = isMember;
    }

    public void updateIsFollowing(Boolean isFollowing) {
        this.isFollowing = isFollowing;
    }
}
