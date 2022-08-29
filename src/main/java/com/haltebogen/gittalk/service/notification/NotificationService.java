package com.haltebogen.gittalk.service.notification;


import com.haltebogen.gittalk.dto.notification.NotificationDto;
import com.haltebogen.gittalk.entity.notification.Notification;
import com.haltebogen.gittalk.entity.user.Member;
import com.haltebogen.gittalk.repository.MemberRepository;
import com.haltebogen.gittalk.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Page<Notification> getNotifications(Pageable pageable, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        return notificationRepository.findAllByReceiverOrderByCreatedAtDesc(member, pageable);
    }

    @Transactional
    public NotificationDto createNotification(Member targetMember, NotificationDto notificationDto, Member sender) {
        Notification notification = Notification.builder()
                .notificationDto(notificationDto)
                .receiver(targetMember)
                .build();
        notification.updateSender(sender);
        notificationRepository.save(notification);
        return notificationDto;
    }
}
