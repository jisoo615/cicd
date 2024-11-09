package com.haru.doyak.harudoyak.dto.auth.jwt;

public record JwtRecord(String authorizationType, String accessToken, String refreshToken, Long memberId) {
}
