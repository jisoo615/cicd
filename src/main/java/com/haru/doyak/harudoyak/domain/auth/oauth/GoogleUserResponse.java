package com.haru.doyak.harudoyak.domain.auth.oauth;

import lombok.Data;

@Data
public class GoogleUserResponse {
    String id;
    String email;
    String name;
    String picture;
}
