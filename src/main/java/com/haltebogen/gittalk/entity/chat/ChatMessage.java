package com.haltebogen.gittalk.entity.chat;

import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "messages")
@Data
public class ChatMessage {

    @Id
    private String _id;
    private List<String> participantId;
    private String chatRoomId;

    private String message;
    private MessageStatus messageStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @Nullable
    private MessageAlertType messageAlertType;

    @Builder
    public ChatMessage(List<String> participantId, String chatRoomId,
                       String message, MessageStatus messageStatus,
                       LocalDateTime createdAt,
                       @Nullable MessageAlertType messageAlertType) {
        this.participantId = participantId;
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.messageStatus = messageStatus;
        this.createdAt = createdAt;
        this.messageAlertType = messageAlertType;
    }
}
