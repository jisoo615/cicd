package com.haru.doyak.harudoyak.domain.auth;

import com.haru.doyak.harudoyak.dto.auth.JoinReqDTO;
import com.haru.doyak.harudoyak.dto.auth.JwtMemberDTO;
import com.haru.doyak.harudoyak.dto.auth.JwtReqDTO;
import com.haru.doyak.harudoyak.dto.auth.LoginReqDTO;
import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import com.haru.doyak.harudoyak.entity.Level;
import com.haru.doyak.harudoyak.entity.Member;
import com.haru.doyak.harudoyak.repository.FileRepository;
import com.haru.doyak.harudoyak.repository.LevelRepository;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import com.haru.doyak.harudoyak.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final LevelRepository levelRepository;
    private final FileRepository fileRepository;
    @Value("${spring.oauth2.local.client-name}")
    private String local_client_name;

    public void joinMember(JoinReqDTO joinReqDTO){
        Member member = Member.builder()
                .email(joinReqDTO.getEmail())
                .provider(local_client_name)
                .password(passwordEncoder.encode(joinReqDTO.getPassword()))
                .nickname(joinReqDTO.getNickname())
                .build();

        memberRepository.save(member);
        member.updateLocalProviderId();
        // 레벨 생성하기
        Level level = Level.builder()
                .member(member)
                .point(10L)// 가입시 10포인트
                .build();
        levelRepository.save(level);
    }

    public JwtMemberDTO login(LoginReqDTO loginReqDTO) throws Exception {
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
        return JwtMemberDTO.builder()
                .jwtRecord(jwtRecord)
                .member(savedMember)
                .build();
    }

    public JwtMemberDTO reissue(JwtReqDTO jwtReqDTO) {
        // 검증
        if(jwtProvider.validateToken(jwtReqDTO.getRefreshToken())){
            // rtk로 유저 찾기
            Member savedMember = memberRepository.findMemberByRefreshToken(jwtReqDTO.getRefreshToken()).orElseThrow();
            // 재발급
            JwtRecord jwtRecord = jwtProvider.getJwtRecord(savedMember);
            return JwtMemberDTO.builder()
                    .jwtRecord(jwtRecord)
                    .member(savedMember)
                    .build();
        }
        return null;
    }

    public Level getLevelByMemberId(Long memberId){
        Optional<Level> levelOptional = levelRepository.findLevelByMemberId(memberId);
        Level level = levelOptional.orElseThrow();
        return level;
    }
}
