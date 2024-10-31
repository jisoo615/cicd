package com.haru.doyak.harudoyak.domains.oauth;

import com.haru.doyak.harudoyak.entitys.Member;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleOAuthService {
    private final WebClient webClient = WebClient.create();
    private final MemberRepository memberRepository;

    @Value("${spring.oauth2.google.client-id}")
    private String google_client_id;
    @Value("${spring.oauth2.google.client-secret}")
    private String google_client_secret;
    @Value("${spring.oauth2.google.redirect-uri}")
    private String google_redirect_uri;

    public GoogleTokenResponse requestGoogleAccessToken(String authorizationCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", google_client_id);
        params.put("client_secret", google_client_secret);
        params.put("redirect_uri", google_redirect_uri);
        params.put("code", authorizationCode);
        params.put("grant_type", "authorization_code");

        return webClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(params)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                        response.bodyToMono(String.class).flatMap(body -> {
                            throw new RuntimeException("Error response: " + body);
                        }))
                .bodyToMono(GoogleTokenResponse.class)
                .block();
    }

    public GoogleUserResponse requestGoogleUserInfo(GoogleTokenResponse googleTokenResponse){
        return webClient.get()
                .uri("https://www.googleapis.com/oauth2/v2/userinfo")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+googleTokenResponse.getAccess_token())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                        response.bodyToMono(String.class).flatMap(body -> {
                            throw new RuntimeException("Error response: " + body);
                        }))
                .bodyToMono(GoogleUserResponse.class)
                .block();
    }

    public String googleLogin(String authorizationCode){
        GoogleUserResponse userInfo = requestGoogleUserInfo(requestGoogleAccessToken(authorizationCode));
        // 이메일로 가입된 회원인지 확인하기
        Optional<Member> optionalMember = memberRepository.findMemberByEmail(userInfo.email);
        // 가입 안되어있으면 가입시키기
        if(optionalMember.isEmpty()){

        }
        
        // jwt 토큰 발급하기
        
        return userInfo.toString();
    }
}
