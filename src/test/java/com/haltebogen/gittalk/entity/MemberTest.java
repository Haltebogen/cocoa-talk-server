package com.haltebogen.gittalk.entity;

import com.haltebogen.gittalk.init.InitInstance;
import com.haltebogen.gittalk.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberTest {

    InitInstance initMember = new InitInstance();

    @Autowired
    private MemberRepository memberRepository;
    Member member = initMember.createDefaultMember();

    @BeforeEach
    void setup() {
        memberRepository.save(member);
    }

    @AfterEach
    void afterSetup() {
        memberRepository.delete(member);
    }

    @Test
    @DisplayName("Member 객체에 createdAt 값이 정상적으로 들어간다.")
    public void test_created_at_in_member_성공() {
        Optional<Member> savedMember = memberRepository.findByProviderId(1L);
        savedMember.ifPresent(presentedMember -> {
            assertThat(presentedMember.getCreatedAt()).isNotNull();
        });
    }

    @Test
    @DisplayName("Member 객체에 modifiedAt 값이 정상적으로 들어간다.")
    public void test_modified_at_in_member_성공() {
        Optional<Member> savedMember = memberRepository.findByProviderId(1L);
        savedMember.ifPresent(presentedMember -> {
            assertThat(presentedMember.getModifiedAt()).isNotNull();
        });
    }

    @Test
    @DisplayName("Member 객체에 isRemoved 값이 정상적으로 들어간다.")
    public void test_is_removed_in_member_성공() {
        Optional<Member> savedMember = memberRepository.findByProviderId(1L);
        System.out.println(savedMember.get().getIsRemoved());
        savedMember.ifPresent(presentedMember -> {
            assertThat(presentedMember.getIsRemoved()).isEqualTo(false);
        });
    }
}
