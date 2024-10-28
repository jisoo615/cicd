package com.haru.doyak.harudoyak.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;
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
