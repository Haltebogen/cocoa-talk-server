package com.haltebogen.gittalk.dto.notification;

import com.haltebogen.gittalk.entity.notification.Notification;
import com.haltebogen.gittalk.entity.notification.NotificationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NotificationDto {

    private String title;
    private String link;
    private String message;
    private Boolean isRead;
    private NotificationType notificationType;

    @Builder
    public NotificationDto(Notification notification) {
        this.title = notification.getTitle();
        this.link = notification.getLink();
        this.message = notification.getMessage();
        this.isRead = notification.getIsRead();
        this.notificationType = notification.getNotificationType();
    }
}
