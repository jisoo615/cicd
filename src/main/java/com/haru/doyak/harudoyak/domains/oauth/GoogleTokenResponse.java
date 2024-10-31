package com.haru.doyak.harudoyak.domains.oauth;

import lombok.Data;

@Data
public class GoogleTokenResponse {
    String access_token;
    Integer expires_in;
    String scope;
    String id_token;
}
