package com.haltebogen.gittalk.dto.member;

import com.haltebogen.gittalk.entity.user.FollowStatus;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowRequestDto {
    private Long following;
    private FollowStatus followStatus;

    @Builder
    public FollowRequestDto(Long following, FollowStatus followStatus) {
        this.following = following;
        this.followStatus = followStatus;
    }
}
