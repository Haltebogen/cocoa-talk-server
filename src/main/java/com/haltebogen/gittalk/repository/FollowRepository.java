package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.user.Follow;
import com.haltebogen.gittalk.entity.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollower(Member follower);
}
