package com.haltebogen.gittalk.dto.notification;

import com.haltebogen.gittalk.entity.notification.Notification;
import com.haltebogen.gittalk.entity.notification.NotificationType;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.Getter;

@Getter
public class NotificationResponseDto {

    private String title;
    private String link;
    private String message;
    private Boolean isRead;
    private NotificationType notificationType;
    private Member sender;
    private Member receiver;

    public NotificationResponseDto(Notification notification) {
        this.title = notification.getTitle();
        this.link = notification.getLink();
        this.message = notification.getMessage();
        this.isRead = notification.getIsRead();
        this.notificationType = notification.getNotificationType();
        this.sender = notification.getSender();
        this.receiver = notification.getReceiver();
    }
}
