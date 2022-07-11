package com.haltebogen.gittalk.entity.notification;

import com.haltebogen.gittalk.entity.BaseAuditEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification extends BaseAuditEntity {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String link;
}
