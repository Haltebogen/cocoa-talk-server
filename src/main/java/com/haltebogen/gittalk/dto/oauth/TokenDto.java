package com.haltebogen.gittalk.dto.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDto {
    private String access_token;
    private String token_type;
    private String scope;
}
