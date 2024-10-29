package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogTag {

    @ManyToOne
    @JoinColumn(name = "logId")
    private Log log;

    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;

}
