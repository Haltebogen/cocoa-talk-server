package com.haltebogen.gittalk.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Member following;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Member follower;

    private FollowStatus followStatus;

    @Builder
    public Follow(Member following, Member follower, FollowStatus followStatus) {
        this.following = following;
        this.follower = follower;
        this.followStatus = followStatus;
    }
}
