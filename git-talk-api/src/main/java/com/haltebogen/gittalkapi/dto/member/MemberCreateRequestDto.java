package com.haltebogen.gittalk.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateRequestDto {

    private String email;
    private String name;
    private String nickName;
    private String profileImageUrl;
    private String statusMessage;
    private String bio;
    private String company;
    private Long followersNum;
    private Long followingsNum;

    public MemberCreateRequestDto(
            String email,
            String name,
            String nickName,
            String profileImageUrl,
            String statusMessage,
            String bio,
            String company,
            Long followersNum,
            Long followingsNum) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
        this.statusMessage = statusMessage;
        this.bio = bio;
        this.company = company;
        this.followersNum = followersNum;
        this.followingsNum = followingsNum;
    }
}
