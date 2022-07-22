package com.haltebogen.gittalk.dto.notification;

import com.haltebogen.gittalk.entity.notification.NotificationType;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {

    private String title;
    private String link;
    private String message;
    private Boolean isRead;
    private NotificationType notificationType;
    private Member sender;
    private Member receiver;

    @Builder
    public NotificationResponseDto(String title, String link, String message, Boolean isRead, NotificationType notificationType) {
        this.title = title;
        this.link = link;
        this.message = message;
        this.isRead = isRead;
        this.notificationType = notificationType;
    }
}
