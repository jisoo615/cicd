package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logTagId;

    @ManyToOne
    @JoinColumn(name = "logId")
    private Log log;

    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;

}
