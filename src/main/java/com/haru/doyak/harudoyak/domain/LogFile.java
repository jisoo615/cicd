package com.haru.doyak.harudoyak.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;
    private String fileName;
    private String originFileName;
}
