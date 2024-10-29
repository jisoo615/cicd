package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LeapFile {
    // 서러도약파일 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;     // 서로도약 파일아이디

    @ManyToOne
    @JoinColumn(name = "shDyId")
    private ShareDoyak shareDoyak; // 서로도약 아이디(외래키)

    @NotNull
    private String fileName; // 저장된 파일명

    @NotNull
    private String oriName;  // 업로드시 파일명

}
