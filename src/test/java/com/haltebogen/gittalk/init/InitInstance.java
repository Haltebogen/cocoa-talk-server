package com.haltebogen.gittalk.init;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.ProviderType;

public class InitInstance {

    private final String nickName = "git-talk-admin";
    private final String company = "git-talk";

    //    private final
    public Member createMember() {
        return Member.builder()
                .providerId(1L)
                .providerType(ProviderType.GITHUB)
                .email("test@gitnub.com")
                .nickName("git-talk-admin")
                .company("gittalk")
                .followersUrl("https://github.com/followers-url")
                .followingUrl("https://github.com/follwing-url").build();
    }

    public GithubUserResponseDto createGithubUserResponseDto() {
        GithubUserResponseDto githubUserResponseDto = GithubUserResponseDto.builder()
                .bio("프런트 개발자 입니다!!")
                .id(1234L)
                .login("admin")
                .company("gittalk")
                .followers_url("https://github.com/followers-url")
                .followings_url("https://github.com/follwing-url")
                .followings(10L)
                .followers(20L)
                .name("git-talk-admin").build();
        return githubUserResponseDto;
    }
}
