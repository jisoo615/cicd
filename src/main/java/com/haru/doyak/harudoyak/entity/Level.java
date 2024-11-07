package com.haru.doyak.harudoyak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long levelId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;          // 회원 아이디(외래키)

    private Long recentContinuity;  // 최근연속 작성일
    private Long maxContinuity;     // 최대연속일
    private Long point;             // 경험치
    private Long logCount;          // daily doyak count
    private Long shareDoyakCount;  // share doyak count
    @CreationTimestamp
    private LocalDate firstDate;

    @PrePersist
    public void prePersist() {
        if(maxContinuity == null) this.maxContinuity = 0L;
        if(recentContinuity == null) this.recentContinuity = 0L;
        if(logCount == null) this.logCount = 0L;
        if(shareDoyakCount == null) this.shareDoyakCount = 0L;
    }
}
