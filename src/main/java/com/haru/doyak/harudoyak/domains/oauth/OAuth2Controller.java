package com.haru.doyak.harudoyak.domains.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/oauth/google")
public class OAuth2Controller {
    private final GoogleOAuthService oAuthService;

    @GetMapping("token")
    public ResponseEntity<GoogleTokenResponse> getGoogleToken(@RequestParam("code") String code){

        return ResponseEntity.ok().body(oAuthService.requestGoogleAccessToken(code));
    }

    @GetMapping("user")
    public ResponseEntity<String> getUser(@RequestParam("code") String code){
        return ResponseEntity.ok().body(oAuthService.googleLogin(code));
    }
}
