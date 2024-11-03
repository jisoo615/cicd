package com.haru.doyak.harudoyak.domains.auth;

import com.haru.doyak.harudoyak.domains.auth.oauth.GoogleOAuthService;
import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import com.haru.doyak.harudoyak.dto.jwt.JwtResDTO;
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
    public ResponseEntity<JwtResDTO> login(@RequestBody LoginReqDTO loginReqDTO) throws Exception {
        JwtRecord jwt = authService.login(loginReqDTO);
        JwtResDTO jwtResDTO = JwtResDTO.builder()
                .memberId(jwt.memberId())
                .refreshToken(jwt.refreshToken())
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt.authorizationType()+" "+jwt.accessToken())
                .body(jwtResDTO);
    }

    @PostMapping("login/google")
    public ResponseEntity<JwtResDTO> googleLogin(@RequestBody String code){
        JwtRecord jwt = oAuthService.googleLogin(code);
        JwtResDTO jwtResDTO = JwtResDTO.builder()
                .memberId(jwt.memberId())
                .refreshToken(jwt.refreshToken())
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt.authorizationType()+" "+jwt.accessToken())
                .body(jwtResDTO);
    }

    @PostMapping("join")
    public ResponseEntity<String> join(@RequestBody JoinReqDTO joinReqDto){
        authService.joinMember(joinReqDto);
        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }
}
