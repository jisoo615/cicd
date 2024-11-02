package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member; // 회원 (외래키)

    @OneToMany(mappedBy = "log")
    private List<LogTag> tags;    // 로그&태그 복합키 테이블 사용

    private String content;
    private String emotion;
    private LocalDateTime creationDate;// registration date time
    private Boolean isRead;// read state

    @OneToOne
    @JoinColumn(name = "fileId")
    private File file;

    @PrePersist
    private void prePersist() {
        this.creationDate = LocalDateTime.now();
        this.isRead = false;
    }

    @Builder
    public Log(Member member, String content, String emotion, LocalDateTime creationDate, Boolean isRead, File file) {
        this.member = member;
        this.content = content;
        this.emotion = emotion;
        this.creationDate = creationDate;
        this.isRead = isRead;
        this.file = file;
    }

}
