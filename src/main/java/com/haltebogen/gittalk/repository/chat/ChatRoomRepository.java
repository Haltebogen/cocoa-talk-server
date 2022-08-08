package com.haltebogen.gittalk.repository.chat;

import com.haltebogen.gittalk.entity.chat.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
}
