package com.haltebogen.gittalk.entity.notification;

import com.haltebogen.gittalk.dto.notification.NotificationDto;
import com.haltebogen.gittalk.entity.BaseAuditEntity;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseAuditEntity {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String link;
    private String message;
    @Column(columnDefinition = "boolean default false")
    private Boolean isRead;
    private NotificationType notificationType;

    @Column(columnDefinition = "boolean default false")
    private Boolean isSendPushNotification;

    @ManyToOne
    @Nullable
    private Member sender;
    @ManyToOne
    private Member receiver;

    @Builder
    public Notification(
            NotificationDto notificationDto,
            Member receiver
    ) {
        this.title = notificationDto.getTitle();
        this.link = notificationDto.getLink();
        this.message = notificationDto.getMessage();
        this.isRead = notificationDto.getIsRead();
        this.notificationType = notificationDto.getNotificationType();
        this.receiver = receiver;
    }

    public void updateIsRead() {
        this.isRead = true;
    }

    public void updateSender(Member sender) {
        this.sender = sender;
    }

    public void updateIsSendPushNotification(Boolean isSendPushNotification) {
        this.isSendPushNotification = isSendPushNotification;
    }
}