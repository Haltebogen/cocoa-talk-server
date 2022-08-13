package com.haltebogen.gittalk.entity.chat;

import com.haltebogen.gittalk.dto.member.ChatMemberResponseDto;
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

@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    @Id
    private String _id;

    private String sender;
    private List<Long> receiver;
    private String chatRoomId;

    private String message;
    private MessageStatus messageStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @Nullable
    private MessageAlertType messageAlertType;

}
