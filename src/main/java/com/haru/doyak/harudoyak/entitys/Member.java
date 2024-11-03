package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//@Setter  예시에는 세터를 안쓰는데 그 이유는..?
@Getter
@Entity
@AllArgsConstructor
// AccessLevel.PROTECTED : 접근권한 최소화 (세팅한 값만 사용하기 위해)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    // 회원 엔티티
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;         // 회원아이디

    @OneToMany(mappedBy = "member")
    private List<Doyak> doyaks;    // 복합키 도약 엔티티

    private String email;      // 이메일 주소

    private String password;        // 비밀번호

    private String nickname;   // 닉네임

    private String aiNickname; // 도약이별명

    private String goalName;   // 도약목표명

    private String kakaoId;    // 카카오아이디
    private String googleId;   // 구글아이디
    private Boolean isChecked;   // 이메일인증 상태
    private String refreshToken;
    private String refreshTokenExpireDate;

    /**
     * insert 되기전 (persist 되기전) 실행된다.
     * */
    @PrePersist
    public void prePersist() {
        if (this.isChecked == null) {
            this.isChecked = false;
        }
    }

}
