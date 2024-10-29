package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doyak {
    // 도약 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long doyakId;

    @ManyToOne
    @JoinColumn(name = "shDyId")
    private ShareDoyak shareDoyak;// 서로도약 아이디(외래키)

    @ManyToOne
    @JoinColumn(name = "memId")
    private Member member;// 회원 아이디(외래키)

}
