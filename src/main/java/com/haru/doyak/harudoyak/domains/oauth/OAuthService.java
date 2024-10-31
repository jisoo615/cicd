package com.haru.doyak.harudoyak.domains.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final WebClient webClient = WebClient.create();
    @Value("${oauth2.google.client-id}")
    private final String Google_Client_id;
    @Value("${oauth2.google.client-secret}")
    private final String Google_Client_secret;
    @Value("${oauth2.google.redirect-uri}")
    private final String Google_Redirect_uri;

    public GoogleTokenResponse getGoogleAccessToken(String authorizationCode) {

        return webClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("client_id="+Google_Client_id
                        +"client_secret="+Google_Client_secret
                        +"redirect_uri="+Google_Redirect_uri
                        +"code="+authorizationCode
                        +"grant_type=authorization_code")
                .retrieve()
                .bodyToMono(GoogleTokenResponse.class)
                .block();
    }
}
