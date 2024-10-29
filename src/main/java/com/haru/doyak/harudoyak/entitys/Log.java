package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "memId")
    private Member member; // 회원 (외래키)

    private String content;
    private String emotion;
    private LocalDateTime regDt;// registration date time
    private Boolean readSt;// read state

    @PrePersist
    private void prePersist() {
        this.regDt = LocalDateTime.now();
        this.readSt = false;
    }
}
