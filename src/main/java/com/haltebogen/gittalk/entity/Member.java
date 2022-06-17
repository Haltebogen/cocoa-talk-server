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

    private Long githubId;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;
    private String email;
    private String nickName;
    private String bio;
    private String company;
    private String statusMessage;
    private String profileImageUrl;
    private String followersUrl;
    private String followingUrl;

    @Builder
    public Member(
            Long githubId,
            MemberType memberType,
            String email,
            String nickName,
            String company,
            String followersUrl,
            String followingUrl) {

        this.githubId = githubId;
        this.memberType = memberType;
        this.email = email;
        this.nickName = nickName;
        this.company = company;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
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
