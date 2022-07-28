package com.haltebogen.gittalk.dto.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema
@Getter
@NoArgsConstructor
public class SearchGithubFollowResponseDto {

    private Long providerId;
    private String email;
    private String nickName;
    private String profileImageUrl;
    private String statusMessage;
    private Boolean isFollower;
    private Boolean isFollowing;
    private Boolean isMember;

    @Builder
    public SearchGithubFollowResponseDto(GitUserProfileDto member, Boolean isFollower, Boolean isFollowing, Boolean isMember) {
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

    public void updateIsMember(Boolean isMember) {
        this.isMember = isMember;
    }
}
