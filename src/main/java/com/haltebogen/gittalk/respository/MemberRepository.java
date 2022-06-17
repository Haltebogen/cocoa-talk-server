package com.haltebogen.gittalk.respository;

import com.haltebogen.gittalk.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean isExistByEmail(String email);

    @Query("select m from Member m " +
            "where m.nickName like %:keyword% or m.email like %:keyword% " +
            "order by m.id desc")
    Page<Member> findBySearch(@Param("keyword") String keyword, Pageable pageable);
}
