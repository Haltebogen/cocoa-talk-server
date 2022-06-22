package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.init.InitInstance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    InitInstance initMember = new InitInstance();

    @Nested
    @DisplayName("멤버 생성 테스트")
    class TestJoinMember {
        @Test
        @Transactional
        @DisplayName("멤버 생성이 성공한다.")
        public void test_join_member_성공() {

            Member member = initMember.createMember();

            Member joinedMember = memberRepository.save(member);
            Optional<Member> targetMember = memberRepository.findByEmail(joinedMember.getEmail());

            targetMember.ifPresent(presentedMember -> {
                assertThat(presentedMember).isEqualTo(joinedMember);
            });
        }

        @Test
        @DisplayName("Member 의 toString 이 성공한다.")
        public void test_to_string_성공() {
            Member member = initMember.createMember();
            assertThat(ObjectUtils.identityToString(member)).isEqualTo(member.toString());
        }
    }

    @Nested
    @DisplayName("멤버 정보 업데이트 테스트")
    class TestUpdateMember {
        @Test
        @Transactional
        @DisplayName("멤버의 소개 업데이트가 성공한다.")
        public void test_bio_update_성공() {
            Member member = initMember.createMember();
            String bio = "안녕하세요! 백엔드 개발자입니다!";

            member.updateBio(bio);
            Member joinedMember = memberRepository.save(member);
            Optional<Member> targetMember = memberRepository.findByEmail(joinedMember.getEmail());

            targetMember.ifPresent(presentedMember -> {
                assertThat(presentedMember.getBio()).isEqualTo(joinedMember.getBio());
            });
        }

        @Test
        @Transactional
        @DisplayName("멤버의 상태 메시지 업데이트가 성공한다.")
        public void testUpdateMemberStatusMessage() {
            Member member = initMember.createMember();
            String statusMessage = "오늘도 즐거운 코딩!!";

            member.updateStatusMessage(statusMessage);
            Member joinedMember = memberRepository.save(member);
            Optional<Member> targetMember = memberRepository.findByEmail(joinedMember.getEmail());

            targetMember.ifPresent(presentedMember -> {
                assertThat(presentedMember.getStatusMessage()).isEqualTo(joinedMember.getStatusMessage());
            });
        }

        @Test
        @Transactional
        @DisplayName("멤버의 프로필 이미지 수정이 성공한다.")
        public void testUpdateMemberBio() {
            Member member = initMember.createMember();
            String profileImageUrl = "https://github.com/admin/profile-image-url";

            member.updateProfileImageUrl(profileImageUrl);
            Member joinedMember = memberRepository.save(member);
            Optional<Member> targetMember = memberRepository.findByEmail(joinedMember.getEmail());

            targetMember.ifPresent(presentedMember -> {
                assertThat(presentedMember.getProfileImageUrl()).isEqualTo(joinedMember.getProfileImageUrl());
            });
        }
    }
}
