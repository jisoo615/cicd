package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public Log(Member member, String content, String emotion, LocalDateTime creation_date, Boolean isRead) {
        this.member = member;
        this.content = content;
        this.emotion = emotion;
        this.creation_date = creation_date;
        this.isRead = isRead;
    }

}
