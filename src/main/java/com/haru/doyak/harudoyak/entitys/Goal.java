package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goal {

    @Id
    private Long goalId; // 목표 아이디

    @OneToOne
    @JoinColumn(name = "memId")
    private Member member; // 회원 아이디(외래키)

    private String name; // 목표명

}
