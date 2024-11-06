package com.haru.doyak.harudoyak.dto.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResDTO {
    private String refreshToken;
    private Long memberId;
}
