package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterId;

    private String content;

    @CreationTimestamp
    private Date regDt;
    private LocalDateTime arrivedDt;// registration date time

    @PrePersist
    private void prePersist() {
        this.arrivedDt = LocalDateTime.now().plusHours(10);
    }

}
