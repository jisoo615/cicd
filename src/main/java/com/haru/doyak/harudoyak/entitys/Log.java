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
    @JoinColumn(name = "memberId")
    private Member member; // 회원 (외래키)

    private String content;
    private String emotion;
    private LocalDateTime creation_date;// registration date time
    private Boolean isRead;// read state

    @OneToOne
    @JoinColumn(name = "fileId")
    private File file;

    @PrePersist
    private void prePersist() {
        this.creation_date = LocalDateTime.now();
        this.isRead = false;
    }
}
