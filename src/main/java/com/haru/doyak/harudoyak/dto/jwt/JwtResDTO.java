package com.haru.doyak.harudoyak.dto.jwt;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResDTO {
    String refreshToken;
    Long memberId;
}
