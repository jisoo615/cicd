package com.haru.doyak.harudoyak.domain.auth;

import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import com.haru.doyak.harudoyak.entity.Member;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import com.haru.doyak.harudoyak.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void joinMember(JoinReqDTO joinReqDTO){
        Member member = Member.builder()
                .email(joinReqDTO.getEmail())
                .password(passwordEncoder.encode(joinReqDTO.getPassword()))
                .nickname(joinReqDTO.getNickname())
                .build();

        memberRepository.save(member);
    }

    public JwtRecord login(LoginReqDTO loginReqDTO) throws Exception {
        Optional<Member> memberOptional = memberRepository.findMemberByEmail(loginReqDTO.getEmail());
        if(memberOptional.isEmpty()){
            throw new Exception("member no exist");
        }
        Member savedMember = memberOptional.get();
        if(!passwordEncoder.matches(loginReqDTO.getPassword(), savedMember.getPassword())){
            throw new Exception("wrong password");
        }
        // 토큰 발행
        JwtRecord jwtRecord = jwtProvider.getJwtRecord(savedMember);
        savedMember.updateRefreshToken(jwtRecord.refreshToken());
        memberRepository.save(savedMember);

        return jwtRecord;
    }
}
