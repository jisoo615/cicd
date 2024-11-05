package com.haru.doyak.harudoyak.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShareDoyak {
    // 서로도약 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareDoyakId;          // 서로도약pk

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;              // 회원 아이디(외래키)

    @OneToOne
    @JoinColumn(name = "fileId")
    private File file;                  // 파일 아이디(외래키)

    @OneToMany(mappedBy = "shareDoyak")
    private List<Doyak> doyaks;         // 복합키 도약 엔티티

    /*@NotNull*/
    private String title;               // 서로도약 제목

    @NotNull
    private String content;             // 서로도약 글내용

    @CreationTimestamp
    private LocalDateTime creationDate; // 서로도약 글등록일

    @PrePersist
    private void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    @Builder
    public ShareDoyak(Member member, File file, String title, String content) {
        this.member = member;
        this.file = file;
        this.title = title;
        this.content = content;
    }

}
