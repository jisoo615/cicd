package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    // 태그 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @OneToMany(mappedBy = "tag")
    private List<LogTag> logs;    // 로그&태그 복합키 테이블 사용

    @NotNull
    private String name;

    @Builder
    public Tag(String name, List<LogTag> logs) {
        this.name = name;
        this.logs = logs;
    }

}
