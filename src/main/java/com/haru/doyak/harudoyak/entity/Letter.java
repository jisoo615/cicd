package com.haru.doyak.harudoyak.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterId;

    private String content;

    private LocalDateTime creationDate;

    private LocalDateTime arrivedDate;

    @ManyToOne
    @JoinColumn(name = "logId")
    private Log log;// 회원 아이디(외래키)

    @PrePersist
    private void prePersist() {
        this.creationDate = LocalDateTime.now();
        this.arrivedDate = this.creationDate.plusHours(10);
    }

}
