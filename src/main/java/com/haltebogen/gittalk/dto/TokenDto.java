package com.haltebogen.gittalk.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String access_token;
    private String token_type;
    private String scope;
}
