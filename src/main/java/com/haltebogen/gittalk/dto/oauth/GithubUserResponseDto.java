package com.haltebogen.gittalk.dto.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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

}
