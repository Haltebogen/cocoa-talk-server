package com.haltebogen.gittalk.dto.oauth;

import com.haltebogen.gittalk.entity.Member;
import lombok.Data;
@Data
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
