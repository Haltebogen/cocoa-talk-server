package com.haltebogen.gittalk.service.user;

import com.haltebogen.gittalk.dto.member.FollowRequestDto;
import com.haltebogen.gittalk.dto.member.FollowResponseDto;
import com.haltebogen.gittalk.entity.user.Follow;
import com.haltebogen.gittalk.entity.user.FollowStatus;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.repository.FollowRepository;
import com.haltebogen.gittalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FollowResponseDto createFollow(Long memberId, FollowRequestDto followTargetMember) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Follow follow = Follow.builder()
                .follower(member)
                .following(followTargetMember.getFollowing())
                .followStatus(FollowStatus.PENDING)
                .build();
        followRepository.save(follow);
        FollowResponseDto followResponseDto = FollowResponseDto.builder()
                .follower(member)
                .following(followTargetMember.getFollowing())
                .followStatus(FollowStatus.PENDING)
                .build();
        return followResponseDto;
    }
}
