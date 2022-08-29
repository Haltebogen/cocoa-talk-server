package com.haltebogen.gittalk.service.user;

import com.haltebogen.gittalk.dto.member.FollowResponseDto;
import com.haltebogen.gittalk.dto.member.MemberDetailResponseDto;
import com.haltebogen.gittalk.dto.notification.NotificationDto;
import com.haltebogen.gittalk.entity.notification.NotificationType;
import com.haltebogen.gittalk.entity.user.Follow;
import com.haltebogen.gittalk.entity.user.FollowStatus;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.repository.FollowRepository;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private static final String FOLLOW_REQUEST_TITLE = "친구 요청";
    private static final String FOLLOW_REQUEST_BODY = "%s님이 친구 요청을 하였습니다.";
    private static final String FOLLOW_REQUEST_LINK = "http://localhost:3000";

    private static final String FOLLOW_ALLOW_TITLE = "친구 요청 수락";
    private static final String FOLLOW_ALLOW_BODY = "%s님이 친구 요청을 수락하였습니다.";
    private static final String FOLLOW_ALLOW_LINK = "http://localhost:3000";
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final NotificationService notificationService;

    @Transactional
    public FollowResponseDto createFollowRequest(Long memberId, Long followTargetMemberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Member targetMember = memberRepository.findByProviderId(followTargetMemberId).orElseThrow(IllegalArgumentException::new);

        Follow follow = Follow.builder()
                .follower(member)
                .following(targetMember)
                .followStatus(FollowStatus.PENDING)
                .build();
        followRepository.save(follow);

        NotificationDto notificationDto = NotificationDto.builder()
                .title(String.format(FOLLOW_REQUEST_TITLE))
                .link(FOLLOW_REQUEST_LINK)
                .message(String.format(FOLLOW_REQUEST_BODY, member.getNickName()))
                .isRead(false)
                .notificationType(NotificationType.FOLLOW_REQUEST)
                .build();

        notificationService.createNotification(targetMember, notificationDto);
        return FollowResponseDto.builder()
                .follower(new MemberDetailResponseDto(member))
                .following(new MemberDetailResponseDto(targetMember))
                .followStatus(FollowStatus.PENDING)
                .build();
    }

    public FollowResponseDto createFollowAllow(Long memberId, Long followTargetMemberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        Member targetMember = memberRepository.findById(followTargetMemberId).orElseThrow(IllegalArgumentException::new);
        Follow follow = followRepository.findByFollowingAndFollowStatus(member, FollowStatus.PENDING);

        follow.updateFollowStatus(FollowStatus.COMPLETED);

        NotificationDto notificationDto = NotificationDto.builder()
                .title(String.format(FOLLOW_ALLOW_TITLE))
                .link(FOLLOW_ALLOW_LINK)
                .message(String.format(FOLLOW_ALLOW_BODY, member.getNickName()))
                .isRead(false)
                .notificationType(NotificationType.FOLLOW_ALLOW)
                .build();

        notificationService.createNotification(targetMember, notificationDto);

        return FollowResponseDto.builder()
                .follower(new MemberDetailResponseDto(member))
                .following(new MemberDetailResponseDto(follow.getFollowing()))
                .followStatus(FollowStatus.COMPLETED)
                .build();
    }


        @Transactional
    public List<MemberDetailResponseDto> getFollowers(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        List<Follow> follows = followRepository.findAllByFollowerAndFollowStatus(member, FollowStatus.COMPLETED);
        return follows.stream().map(follow -> new MemberDetailResponseDto(follow.getFollowing())).collect(Collectors.toList());
    }
}
