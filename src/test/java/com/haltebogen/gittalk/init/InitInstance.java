package com.haltebogen.gittalk.init;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.ProviderType;

public class InitInstance {
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
        GithubUserResponseDto githubUserResponseDto = new GithubUserResponseDto();
        githubUserResponseDto.setBio("프런트 개발자 입니다!!");
        githubUserResponseDto.setId(1234L);
        githubUserResponseDto.setLogin("admin");
        githubUserResponseDto.setCompany("gittalk");
        githubUserResponseDto.setFollowers_url("https://github.com/followers-url");
        githubUserResponseDto.setFollowings_url("https://github.com/follwing-url");
        githubUserResponseDto.setFollowings(10L);
        githubUserResponseDto.setFollowers(20L);
        githubUserResponseDto.setName("git-talk-admin");
        return githubUserResponseDto;
    }
}
