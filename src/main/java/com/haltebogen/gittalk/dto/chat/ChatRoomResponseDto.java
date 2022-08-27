package com.haltebogen.gittalk.dto.chat;

import com.haltebogen.gittalk.entity.chat.ChatMessage;
import com.haltebogen.gittalk.entity.chat.ChatRoom;
import com.haltebogen.gittalk.entity.chat.MediaType;
import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatRoomResponseDto {

    private String _id;

    private String roomName;

    private List<ChatMessage> messages;

    private List<Long> participantsId;

    private LocalDateTime createdAt;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this._id = chatRoom.get_id();
        this.roomName = chatRoom.getRoomName();
        this.messages = chatRoom.getMessages();
        this.participantsId = chatRoom.getParticipantsId();
        this.createdAt = chatRoom.getCreatedAt();
    }

    @Builder
    public ChatRoomResponseDto(String _id, String roomName, List<ChatMessage> messages, List<Long> participantsId, LocalDateTime createdAt) {
        this._id = _id;
        this.roomName = roomName;
        this.messages = messages;
        this.participantsId = participantsId;
        this.createdAt = createdAt;
    }
}

