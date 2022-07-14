package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.notification.Notification;
import com.haltebogen.gittalk.entity.user.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findAllByMemberOrderByCreatedAt(Member member, Pageable pageable);

}
