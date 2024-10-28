package com.haru.doyak.harudoyak.domain;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long letterId;
    private String content;
    private LocalDateTime regDt;// registration date time

    @PrePersist
    private void prePersist() {
        this.regDt = LocalDateTime.now().plusHours(10);
    }

}
