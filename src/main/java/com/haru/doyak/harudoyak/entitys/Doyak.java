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

    @EmbeddedId
    private DoyakId doyakId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shareDoyakId")
    @JoinColumn(name = "share_doyak_id")
    private ShareDoyak shareDoyak;// 서로도약 아이디(외래키)

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    private Member member;// 회원 아이디(외래키)

}
