package com.haltebogen.gittalk.entity.notification;

import com.haltebogen.gittalk.entity.BaseAuditEntity;
import com.haltebogen.gittalk.entity.user.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseAuditEntity {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String link;
    private String message;
    private Boolean isRead;
    private NotificationType notificationType;

    @ManyToOne
    private Member member;

    @Builder
    public Notification(
            String title,
            String link,
            String message,
            Boolean isRead,
            NotificationType notificationType
    ) {
        this.title = title;
        this.link = link;
        this.message = message;
        this.isRead = isRead;
        this.notificationType = notificationType;
    }
}
