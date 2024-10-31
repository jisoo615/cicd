package com.haru.doyak.harudoyak.domains.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/oauth/google")
public class OAuth2Controller {
    private final OAuthService oAuthService;

    @GetMapping("token")
    public ResponseEntity getGoogleToken(@RequestParam String code){
        return ResponseEntity.ok().body(oAuthService.getGoogleAccessToken(code));
    }
}
