package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShareDoyak {
    // 서로도약 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareDoyakId;    // 서로도약pk

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;// 회원 아이디(외래키)

//    @EmbeddedId
    private Doyak doyak;    //복합키 테이블 사용

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
