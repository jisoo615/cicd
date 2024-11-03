package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor  // 기본 생성자
@EqualsAndHashCode
public class LogTagId implements Serializable {
    // log와 tag 외래 키를 포함한 기본키 클래스

    private Long logId; // 도약기록아이디

    private Long tagId; // 태그아이디

}
