package com.haltebogen.gittalk.dto.oauth;

import lombok.*;

@Getter
@NoArgsConstructor
public class GithubUserResponseDto {

    private Long id;
    private String login;
    private String avatar_url;
    private String followers_url;
    private String followings_url;
    private String name;
    private String bio;
    private String company;
    private Long followers;
    private Long followings;

    @Builder
    public GithubUserResponseDto(
            Long id,
            String login,
            String avatar_url,
            String followers_url,
            String followings_url,
            String name,
            String bio,
            String company,
            Long followers,
            Long followings) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.followers_url = followers_url;
        this.followings_url = followings_url;
        this.name = name;
        this.bio = bio;
        this.company = company;
        this.followers = followers;
        this.followings = followings;
    }

}
