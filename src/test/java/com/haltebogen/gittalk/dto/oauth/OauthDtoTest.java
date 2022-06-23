package com.haltebogen.gittalk.dto.oauth;

import com.haltebogen.gittalk.init.InitInstance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class OauthDtoTest {

    InitInstance initMember = new InitInstance();

    @Nested
    @DisplayName("Oauth Dto 테스트")
    class TestOauthDto {

        @Test
        @DisplayName("멤버 생성이 성공한다.")
        public void test_create_token_dto_성공() {
            TokenDto tokenDto = initMember.createTokenDto();

            assertThat(tokenDto.getAccess_token()).isEqualTo("access_token");
            assertThat(tokenDto.getToken_type()).isEqualTo("bearer");
            assertThat(tokenDto.getScope()).isEqualTo("");
        }
    }
}
