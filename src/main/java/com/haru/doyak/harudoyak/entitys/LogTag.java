package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
//@Embeddable
//@AllArgsConstructor
//@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogTag implements Serializable {
    // 로그&태그 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logId")
    private Log log;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagId")
    private Tag tag;

}
