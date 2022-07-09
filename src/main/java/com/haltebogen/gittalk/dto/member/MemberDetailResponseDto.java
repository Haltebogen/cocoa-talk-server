package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.user.Member;
import lombok.Getter;

@Getter
public class MemberDetailResponseDto {

    private String email;
    private String name;
    private String nickName;
    private String profileImageUrl;
    private String statusMessage;
    private String bio;
    private String company;
    private Long followersNum;
    private Long followingsNum;

    public MemberDetailResponseDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.nickName = member.getNickName();
        this.profileImageUrl = member.getProfileImageUrl();
        this.statusMessage = member.getStatusMessage();
        this.followersNum = member.getFollowersNum();
        this.followingsNum = member.getFollowingsNum();
        this.bio = member.getBio();
        this.company = member.getCompany();
    }
}
