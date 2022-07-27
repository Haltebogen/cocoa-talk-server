package com.haltebogen.gittalk.entity.chat;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
public class ChatRoomParticipant {

    @Id
    private String _id;
    private List<String> memberId;
    private String chatRoomId;
    private String name;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime lastReadAt;

    @Builder
    public ChatRoomParticipant(List<String> memberId, String chatRoomId, String name, LocalDateTime createdAt, LocalDateTime lastReadAt) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
        this.name = name;
        this.createdAt = createdAt;
        this.lastReadAt = lastReadAt;
    }
}
