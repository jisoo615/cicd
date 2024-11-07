package com.haru.doyak.harudoyak.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;          // 회원 아이디(외래키)

    private Long recentContinuity;  // 최근연속 작성일
    private Long maxContinuity;     // 최대연속일
    private Long point;             // 경험치
    private Long logCount;          // daily doyak count
    private Long shareDoyakCount;  // share doyak count
}
