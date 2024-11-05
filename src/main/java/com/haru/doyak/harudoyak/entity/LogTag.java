package com.haru.doyak.harudoyak.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogTag {
    // 로그&태그 엔티티

    @EmbeddedId
    private LogTagId logTagId;  // 기본키 클래스

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("logId")            // LogTagId.logId와 매핑
    @JoinColumn(name = "log_id")
    private Log log;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")            // LogTagId.tagId와 매핑
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public LogTag(LogTagId logTagId, Log log, Tag tag) {
        this.logTagId = logTagId;
        this.log = log;
        this.tag = tag;
    }

}
