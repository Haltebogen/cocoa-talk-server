package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.user.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
