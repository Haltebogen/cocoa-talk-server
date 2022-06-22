package com.haltebogen.gittalk.init;

import com.haltebogen.gittalk.dto.oauth.GithubUserResponseDto;
import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.ProviderType;

public class InitInstance {

    private final String NICKNAME = "git-talk-admin";
    private final String COMPANY = "git-talk";
    private final String FOLLOWERS_URL = "https://github.com/followers-url";
    private final String FOLLOWING_URL = "https://github.com/following-url";
    private final String NAME = "sehoon";
    private final String BIO = "안녕하세요!! 백엔드 개발자 입니다!";

    //    private final
    public Member createMember() {
        return Member.builder()
                .providerId(1L)
                .providerType(ProviderType.GITHUB)
                .email("test@gitnub.com")
                .nickName(NICKNAME)
                .name(NAME)
                .bio(BIO)
                .company(COMPANY)
                .followersUrl(FOLLOWERS_URL)
                .followingUrl(FOLLOWING_URL).build();
    }

    public GithubUserResponseDto createGithubUserResponseDto() {
        GithubUserResponseDto githubUserResponseDto = GithubUserResponseDto.builder()
                .bio(BIO)
                .id(1234L)
                .login(NICKNAME)
                .company(COMPANY)
                .followers_url(FOLLOWERS_URL)
                .followings_url(FOLLOWING_URL)
                .followings(10L)
                .followers(20L)
                .name(NAME).build();
        return githubUserResponseDto;
    }
}
