package com.haru.doyak.harudoyak.domain.auth;

import com.haru.doyak.harudoyak.domain.auth.oauth.GoogleOAuthService;
import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.JwtMemberDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import com.haru.doyak.harudoyak.dto.auth.LoginResDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final GoogleOAuthService oAuthService;
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("login")
    public ResponseEntity<LoginResDTO> login(@RequestBody LoginReqDTO loginReqDTO) throws Exception {
        JwtMemberDTO jwtMemberDTO = authService.login(loginReqDTO);
        LoginResDTO loginResDTO = LoginResDTO.builder()
                .memberId(jwtMemberDTO.getMember().getMemberId())
                .aiNickname(jwtMemberDTO.getMember().getAiNickname())
                .refreshToken(jwtMemberDTO.getJwtRecord().refreshToken())
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtMemberDTO.getJwtRecord().authorizationType()+" "+jwtMemberDTO.getJwtRecord().accessToken())
                .body(loginResDTO);
    }

    @PostMapping("login/google")
    public ResponseEntity<LoginResDTO> googleLogin(@RequestBody String code){
        JwtMemberDTO jwtMemberDTO = oAuthService.googleLogin(code);
        LoginResDTO loginResDTO = LoginResDTO.builder()
                .memberId(jwtMemberDTO.getMember().getMemberId())
                .aiNickname(jwtMemberDTO.getMember().getAiNickname())
                .refreshToken(jwtMemberDTO.getJwtRecord().refreshToken())
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtMemberDTO.getJwtRecord().authorizationType()+" "+jwtMemberDTO.getJwtRecord().accessToken())
                .body(loginResDTO);
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
