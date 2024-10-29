package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

//@Setter  예시에는 세터를 안쓰는데 그 이유는..?
@Getter
@Entity
// AccessLevel.PROTECTED : 접근권한 최소화 (세팅한 값만 사용하기 위해)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    // 회원 엔티티
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memId;         // 회원아이디
    @Column(unique = true)
    private String email;      // 이메일 주소
    @NotNull
    private String pwd;        // 비밀번호
    @NotNull
    private String nickName;   // 닉네임
    @NotNull
    private String aiNickName; // 도약이별명
    @NotNull
    private String goalName;   // 도약목표명

    private String kakaoId;    // 카카오아이디
    private String googleId;   // 구글아이디
    private Boolean emailSt;   // 이메일인증 상태

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        if (this.emailSt == null) {
            this.emailSt = false;
        }
    }

    @Builder
    public Member(String email, String pwd, String nickName, String aiNickName, String goalName, String kakaoId, String googleId) {
        this.email = email;
        this.pwd = pwd;
    }

}
