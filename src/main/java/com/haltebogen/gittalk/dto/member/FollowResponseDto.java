package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.user.FollowStatus;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowResponseDto {

    private Member following;
    private Member follower;
    private FollowStatus followStatus;

    @Builder
    public FollowResponseDto(Member following, Member follower, FollowStatus followStatus) {
        this.following = following;
        this.follower = follower;
        this.followStatus = followStatus;
    }
}
