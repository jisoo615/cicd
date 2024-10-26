package com.haru.doyak.harudoyak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Setter  예시에는 세터를 안쓰는데 그 이유는..?
@Getter
@Entity
// AccessLevel.PROTECTED : 접근권한 최소화 (세팅한 값만 사용하기 위해)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memId;         // 회원아이디
    private String email;      // 이메일 주소
    private String pwd;        // 비밀번호
    private String nickName;   // 닉네임
    private String aiNickName; // 도약이별명
    private String goalName;   // 도약목표명
    private String kakaoId;    // 카카오아이디
    private String googleId;   // 구글아이디
    private Boolean emailSt;   // 이메일인증 상태

}
