package com.haru.doyak.harudoyak.domain.auth.oauth;

import lombok.Data;

@Data
public class KakaoTokenResponse {
    private String token_type;
    private String access_token;
    private String id_token;
    private String refresh_token;
    private String scope;
}
