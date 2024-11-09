package com.haru.doyak.harudoyak.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterId;              // 편지 아이디

    @ManyToOne
    @JoinColumn(name = "logId")
    private Log log;                    // 로그 아이디(외래키)

    @NotNull
    private String content;             // 편지 내용

    @NotNull
    private LocalDateTime creationDate; // 편지 작성일

    @NotNull
    private LocalDateTime arrivedDate;  // 편지 보여지는 시간

    @PrePersist
    private void prePersist() {
        this.creationDate = LocalDateTime.now();
        this.arrivedDate = this.creationDate.plusHours(10);
    }

    @Builder
    public Letter(String content, Log log) {
        this.content = content;
        this.log = log;
    }

}
