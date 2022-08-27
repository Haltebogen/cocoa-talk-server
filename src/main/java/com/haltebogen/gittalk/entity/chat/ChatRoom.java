package com.haltebogen.gittalk.entity.chat;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "chat_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Id
    private String _id;

    private String roomName;

    @Nullable
    private List<ChatMessage> messages;

    private List<Long> participantsId;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public ChatRoom(String _id, String roomName, List<Long> participantsId, LocalDateTime createdAt) {
        this._id = _id;
        this.roomName = roomName;
        this.participantsId = participantsId;
        this.createdAt = createdAt;
    }
}
