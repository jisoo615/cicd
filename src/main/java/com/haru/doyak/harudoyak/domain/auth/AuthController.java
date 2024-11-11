package com.haru.doyak.harudoyak.domain.auth;

import com.haru.doyak.harudoyak.domain.auth.oauth.OAuthService;
import com.haru.doyak.harudoyak.dto.auth.EmailVerifyReqDTO;
import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginResDTO;
import com.haru.doyak.harudoyak.dto.auth.jwt.JwtMemberDTO;
import com.haru.doyak.harudoyak.dto.auth.jwt.JwtReqDTO;
import com.haru.doyak.harudoyak.dto.auth.jwt.JwtResDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final OAuthService oAuthService;
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("login")
    public ResponseEntity<LoginResDTO> login(@RequestBody LoginReqDTO loginReqDTO) throws Exception {
        JwtMemberDTO jwtMemberDTO = authService.login(loginReqDTO);
        LoginResDTO loginResDTO = authService.makeLoginResDTO(jwtMemberDTO.getMember().getMemberId());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtMemberDTO.getJwtRecord().authorizationType()+" "+jwtMemberDTO.getJwtRecord().accessToken())
                .body(loginResDTO);
    }

    @PostMapping("login/google")
    public ResponseEntity<LoginResDTO> googleLogin(@RequestBody LoginReqDTO loginReqDTO){
        JwtMemberDTO jwtMemberDTO = oAuthService.googleLogin(loginReqDTO.getCode());
        LoginResDTO loginResDTO = authService.makeLoginResDTO(jwtMemberDTO.getMember().getMemberId());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtMemberDTO.getJwtRecord().authorizationType()+" "+jwtMemberDTO.getJwtRecord().accessToken())
                .body(loginResDTO);
    }

    @PostMapping("login/kakao")
    public ResponseEntity kakaoLogin(@RequestBody LoginReqDTO loginReqDTO) throws Exception {
        JwtMemberDTO jwtMemberDTO = oAuthService.kakaoLogin(loginReqDTO.getCode());
        LoginResDTO loginResDTO = authService.makeLoginResDTO(jwtMemberDTO.getMember().getMemberId());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtMemberDTO.getJwtRecord().authorizationType()+" "+jwtMemberDTO.getJwtRecord().accessToken())
                .body(loginResDTO);
    }

    @PostMapping("join")
    public ResponseEntity<String> join(@RequestBody JoinReqDTO joinReqDto){
        if(!joinReqDto.getIsVerified()) return ResponseEntity.badRequest().body("이메일 인증이 필요합니다.");
        authService.joinMember(joinReqDto);
        return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
    }

    @PostMapping("email/verify")
    public ResponseEntity<String> emailVerify(@RequestBody EmailVerifyReqDTO dto) throws MessagingException {
        try {
            // 이메일 주소 유효성 검사 후 처리
            System.out.println(dto.toString());
            System.out.println(dto.getEmail());

            String trimedEmail = dto.getEmail().trim();
            InternetAddress emailAddr = new InternetAddress(trimedEmail);
            emailAddr.validate();
            System.out.println(emailAddr.getAddress());

            emailService.sendAuthLinkEmail(emailAddr.getAddress());
            return ResponseEntity.status(HttpStatus.OK).body("인증 메일이 발송되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청");
    }

    @PostMapping("validate")
    public ResponseEntity validate(){
        Object object = RequestContextHolder.getRequestAttributes().getAttribute("authenticated", RequestAttributes.SCOPE_REQUEST);
        return ResponseEntity.ok().body(object.toString());
    }

    @PostMapping("reissue")
    public ResponseEntity<JwtResDTO> reissue(@RequestBody JwtReqDTO jwtReqDTO){
        JwtMemberDTO jwtMemberDTO = authService.reissue(jwtReqDTO);
        JwtResDTO jwtResDTO = JwtResDTO.builder()
                .memberId(jwtMemberDTO.getMember().getMemberId())
                .refreshToken(jwtMemberDTO.getJwtRecord().refreshToken())
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtMemberDTO.getJwtRecord().authorizationType()+" "+jwtMemberDTO.getJwtRecord().accessToken())
                .body(jwtResDTO);
    }
}
