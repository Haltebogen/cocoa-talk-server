package com.haltebogen.gittalk.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GitUserProfileDto {
    private String email;
    private String login;
    private String avatar_url;
    private String bio;

    @Builder
    public GitUserProfileDto(String login, String avatar_url, String bio) {
        this.email = null;
        this.login = login;
        this.avatar_url = avatar_url;
        this.bio = bio;
    }
}
