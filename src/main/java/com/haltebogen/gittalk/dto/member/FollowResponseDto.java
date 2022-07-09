package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.user.FollowStatus;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowResponseDto {

    private MemberDetailResponseDto following;
    private MemberDetailResponseDto follower;
    private FollowStatus followStatus;

    @Builder
    public FollowResponseDto(MemberDetailResponseDto following, MemberDetailResponseDto follower, FollowStatus followStatus) {
        this.following = following;
        this.follower = follower;
        this.followStatus = followStatus;
    }
}
