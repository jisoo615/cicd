package com.haru.doyak.harudoyak.domains.auth;

import com.haru.doyak.harudoyak.domains.auth.oauth.GoogleOAuthService;
import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import com.haru.doyak.harudoyak.dto.jwt.JwtResDTO;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final GoogleOAuthService oAuthService;
    private final AuthService authService;
    private final EmailService emailService;

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

    @PostMapping("email/verify")
    public ResponseEntity<String> emailVerify(@RequestBody String email) throws MessagingException {
        emailService.sendAuthLinkEmail(email);
        return ResponseEntity.ok().body("인증 메일이 발송되었습니다.");
    }

    @PostMapping("validate")
    public ResponseEntity validate(){
        Object object = RequestContextHolder.getRequestAttributes().getAttribute("authenticated", RequestAttributes.SCOPE_REQUEST);
        return ResponseEntity.ok().body(object.toString());
    }
}
