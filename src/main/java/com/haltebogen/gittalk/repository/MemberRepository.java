package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    List<Member> findAllByProviderId(Long providerId);
    Optional<Member> findByProviderId(Long providerId);

    boolean existsByEmail(String email);

    @Query("select m from Member m " +
            "where m.nickName like %:keyword% or m.email like %:keyword% " +
            "order by m.id desc")
    Page<Member> findBySearch(@Param("keyword") String keyword, Pageable pageable);
}
