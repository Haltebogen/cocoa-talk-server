package com.haltebogen.gittalk.entity.chat;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "rooms")
@Data
public class ChatRoom {

    @Id
    private String _id;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime editedAt;

    @Builder
    public ChatRoom(LocalDateTime createdAt, LocalDateTime editedAt) {
        this.createdAt = createdAt;
        this.editedAt = editedAt;
    }
}
