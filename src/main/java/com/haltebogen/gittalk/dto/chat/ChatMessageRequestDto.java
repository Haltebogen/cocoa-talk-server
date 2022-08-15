package com.haltebogen.gittalk.dto.chat;

import com.haltebogen.gittalk.entity.chat.MessageStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatMessageRequestDto {

    private String _id;

    private String sender;

    private List<Long> receiver;

    private String chatRoomId;

    private String message;

    private MessageStatus messageStatus;

    private LocalDateTime createdAt;

    @Builder
    public ChatMessageRequestDto(String _id, String sender, List<Long> receiver, String chatRoomId, String message, MessageStatus messageStatus, LocalDateTime createdAt) {
        this._id = _id;
        this.sender = sender;
        this.receiver = receiver;
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.messageStatus = messageStatus;
        this.createdAt = createdAt;
    }
}
