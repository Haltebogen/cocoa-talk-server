package com.haltebogen.gittalk.entity.chat;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_media")
@Data
public class ChatRoomMedia {

    @Id
    private String _id;
    private String chatRoomId;
    private MediaType mediaType;
    private String uri;

    @Builder
    public ChatRoomMedia(String chatRoomId, MediaType mediaType, String uri) {
        this.chatRoomId = chatRoomId;
        this.mediaType = mediaType;
        this.uri = uri;
    }
}
