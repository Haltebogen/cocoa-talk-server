package com.haltebogen.gittalk.repository.chat;

import com.haltebogen.gittalk.entity.chat.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
