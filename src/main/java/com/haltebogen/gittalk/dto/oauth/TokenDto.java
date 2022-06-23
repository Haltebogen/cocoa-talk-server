package com.haltebogen.gittalk.dto.oauth;

import lombok.*;

@Getter
@NoArgsConstructor
public class TokenDto {
    private String access_token;
    private String token_type;
    private String scope;

    @Builder
    public TokenDto(String access_token, String token_type, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.scope = scope;
    }
}
