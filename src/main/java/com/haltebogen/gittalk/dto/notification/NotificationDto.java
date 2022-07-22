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
    public NotificationDto(String title, String link, String message, Boolean isRead, NotificationType notificationType) {
        this.title = title;
        this.link = link;
        this.message = message;
        this.isRead = isRead;
        this.notificationType = notificationType;
    }
}
