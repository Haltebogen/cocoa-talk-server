package com.haltebogen.gittalk.service.user;

import com.haltebogen.gittalk.dto.member.FollowRequestDto;
import com.haltebogen.gittalk.dto.member.FollowResponseDto;
import com.haltebogen.gittalk.dto.member.MemberDetailResponseDto;
import com.haltebogen.gittalk.dto.member.MemberResponseDto;
import com.haltebogen.gittalk.entity.user.Follow;
import com.haltebogen.gittalk.entity.user.FollowStatus;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.repository.FollowRepository;
import com.haltebogen.gittalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FollowResponseDto createFollow(Long memberId, Long followTargetMemberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Member targetMember = memberRepository.findById(followTargetMemberId).orElseThrow(IllegalArgumentException::new);

        Follow follow = Follow.builder()
                .follower(member)
                .following(targetMember)
                .followStatus(FollowStatus.PENDING)
                .build();
        followRepository.save(follow);
        return FollowResponseDto.builder()
                .follower(new MemberDetailResponseDto(member))
                .following(new MemberDetailResponseDto(targetMember))
                .followStatus(FollowStatus.PENDING)
                .build();
    }

    @Transactional
    public List<MemberResponseDto> getFollowers(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        List<Follow> follows = followRepository.findAllByFollower(member);
        return follows.stream().map(follow -> new MemberResponseDto(follow.getFollowing())).collect(Collectors.toList());
    }
}
