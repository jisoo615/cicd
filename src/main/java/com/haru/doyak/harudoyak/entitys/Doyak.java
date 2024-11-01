package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
//@Embeddable        // 복합키 설정
//@AllArgsConstructor
//@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doyak implements Serializable {
    // 도약 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shareDoyakId")
    private ShareDoyak shareDoyak;// 서로도약 아이디(외래키)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;// 회원 아이디(외래키)

}
