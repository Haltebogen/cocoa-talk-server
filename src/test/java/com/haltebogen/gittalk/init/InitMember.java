package com.haltebogen.gittalk.init;

import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.ProviderType;

public class InitMember {
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
}
