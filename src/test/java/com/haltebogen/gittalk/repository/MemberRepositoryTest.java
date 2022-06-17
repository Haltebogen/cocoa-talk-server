package com.haltebogen.gittalk.repository;

import com.haltebogen.gittalk.entity.Member;
import com.haltebogen.gittalk.entity.MemberType;
import com.haltebogen.gittalk.init.InitMember;
import com.haltebogen.gittalk.respository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    InitMember initMember = new InitMember();

    @Nested
    @DisplayName("멤버 생성 테스트")
    class TestJoinMember {
        @Test
        @Transactional
        @DisplayName("멤버 생성이 성공한다.")
        public void testJoinMember() {


            Member member = initMember.createMember();

            Member joinedMember = memberRepository.save(member);
            Optional<Member> targetMember = memberRepository.findByEmail(joinedMember.getEmail());

            targetMember.ifPresent(presentedMember -> {
                assertThat(presentedMember).isEqualTo(joinedMember);
            });
        }
    }

}
