package com.haltebogen.gittalk.repository.chat;

import com.haltebogen.gittalk.entity.chat.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Override
    Optional<ChatRoom> findById(String s);

    ChatRoom findByChatRoomId(List<Long> participantsId);
}
