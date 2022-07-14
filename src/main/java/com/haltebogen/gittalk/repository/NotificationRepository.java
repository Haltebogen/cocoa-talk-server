package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.notification.Notification;
import com.haltebogen.gittalk.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByMember(Member member);

}
