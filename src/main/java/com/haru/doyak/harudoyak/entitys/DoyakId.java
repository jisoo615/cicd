package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DoyakId implements Serializable {
    // shareDoyak과 member 외래 키를 포함한 기본키 클래스
    
    private Long shareDoyakId; // 서로도약 아이디
    private Long memberId;     // 회원 아이디
    
}
