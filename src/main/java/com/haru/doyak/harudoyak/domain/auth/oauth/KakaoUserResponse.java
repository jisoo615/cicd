package com.haru.doyak.harudoyak.domain.auth.oauth;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class KakaoUserResponse {
    private Long id;
    private LocalDateTime connected_at;
}
