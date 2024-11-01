package com.haru.doyak.harudoyak.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.jwt-key}")
    String keyValue;

    @PostConstruct
    public void init(){
        key = keyValue;
    }

    private static String key;

    public static String generateToken(Map<String, Object> valueMap, int day){
        SecretKey key = null;

        try {
            key = Keys.hmacShaKeyFor(JwtProvider.key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        String jwtStr = Jwts.builder()
                .setHeader(Map.of("typ", "jwt"))
                .setSubject((String)valueMap.get("role"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusDays(day).toInstant()))
                .signWith(key)
                .compact();

        return jwtStr;
    }

    public static Map<String, Object> validateToken(String token){
        token = token;
        Map<String, Object> claim = null;

        try{
            SecretKey Key = Keys.hmacShaKeyFor(JwtProvider.key.getBytes(StandardCharsets.UTF_8));
            claim = Jwts.parserBuilder()
                    .setSigningKey(Key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch  (MalformedJwtException malformedJwtException) {
            throw new RuntimeException(malformedJwtException.getMessage());
        } catch (ExpiredJwtException expiredJwtException) {
            throw new RuntimeException(expiredJwtException.getMessage());
        } catch (InvalidClaimException invalidClaimException) {
            throw new RuntimeException(invalidClaimException.getMessage());
        } catch (JwtException jwtException) {
            throw new RuntimeException(jwtException.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return claim;
    }

    public String validateTokenAndGetSubject(String token) {
        return Jwts.parser()
                .setSigningKey(JwtProvider.key.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


}