package com.haru.doyak.harudoyak.domains.auth;

import com.haru.doyak.harudoyak.domains.auth.oauth.GoogleOAuthService;
import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final GoogleOAuthService oAuthService;
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginReqDTO loginReqDTO) throws Exception {
        JwtRecord jwt = authService.login(loginReqDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt.authorizationType()+" "+jwt.accessToken())
                .body(jwt.accessToken());
    }

    @PostMapping("login/google")
    public ResponseEntity<String> googleLogin(@RequestBody String code){
        JwtRecord jwt = oAuthService.googleLogin(code);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt.authorizationType()+" "+jwt.accessToken())
                .body(jwt.refreshToken());
    }

    @PostMapping("join")
    public ResponseEntity<String> join(@RequestBody JoinReqDTO joinReqDto){
        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }
}
