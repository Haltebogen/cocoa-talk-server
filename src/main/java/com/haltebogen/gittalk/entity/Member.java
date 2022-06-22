package com.haltebogen.gittalk.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseAuditEntity{

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private Long providerId;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;
    private String email;
    private String nickName; // github에서 'login'

    private String name;  // 사용자의 이름
    private String bio;
    private String company;
    private String statusMessage;
    private String profileImageUrl;
    private String followersUrl;
    private String followingUrl;
    private Long followersNum;
    private Long followingsNum;


    @Builder
    public Member(
            Long providerId,
            ProviderType providerType,
            String email,
            String nickName,
            String name,
            String bio,
            String company,
            String profileImageUrl,
            String followersUrl,
            String followingUrl,
            Long followersNum,
            Long followingsNum) {

        this.providerId = providerId;
        this.providerType = providerType;
        this.email = email;
        this.nickName = nickName;
        this.name = name;
        this.bio = bio;
        this.company = company;
        this.profileImageUrl = profileImageUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.followersNum = followersNum;
        this.followingsNum = followingsNum;
    }

    public void updateBio(String bio) {
        this.bio = bio;
    }

    public void updateStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
