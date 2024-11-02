package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
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
    private Long shareDoyakId;    // 서로도약pk

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;// 회원 아이디(외래키)

    @OneToMany(mappedBy = "shareDoyak")
    private List<Doyak> doyaks;

    @NotNull
    private String title;   // 서로도약 제목

    @NotNull
    private String content; // 서로도약 글내용

    @CreationTimestamp
    private LocalDateTime creationDate;     // 서로도약 글등록일

    @OneToOne
    @JoinColumn(name = "fileId")
    private File file;
}
