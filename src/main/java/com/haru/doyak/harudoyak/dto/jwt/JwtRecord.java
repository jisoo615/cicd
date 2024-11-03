package com.haru.doyak.harudoyak.dto.jwt;

import java.util.Map;

public record JwtRecord(String authorizationType, String accessToken, String refreshToken, Long memberId) {
}
