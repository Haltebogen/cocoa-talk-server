package com.haltebogen.gittalk.entity.user;

import com.haltebogen.gittalk.entity.BaseAuditEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseAuditEntity {

    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Member following;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Member follower;

    private FollowStatus followStatus;

    @Builder
    public Follow(Member following, Member follower, FollowStatus followStatus) {
        this.following = following;
        this.follower = follower;
        this.followStatus = followStatus;
    }

    public void updateFollowStatus(FollowStatus followStatus) {
        this.followStatus = followStatus;
    }
}
