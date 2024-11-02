package com.haru.doyak.harudoyak.domains.oauth;

import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
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
    public ResponseEntity<JwtRecord> getUser(@RequestParam("code") String code){
        return ResponseEntity.ok().body(oAuthService.googleLogin(code));
    }

    @GetMapping("login")
    public ResponseEntity<String> getLogin(@RequestParam("code") String code){
        JwtRecord jwt = oAuthService.googleLogin(code);
        return ResponseEntity.ok()
                .header("Authorization", jwt.authorizationType()+" "+jwt.accessToken())
                .body(jwt.refreshToken());
    }
}
