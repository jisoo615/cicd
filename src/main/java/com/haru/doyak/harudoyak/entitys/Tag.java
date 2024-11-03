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
    private Long tagId;           // 태그 아이디

    @OneToMany(mappedBy = "tag")
    private List<LogTag> logs;    // 로그&태그 복합키 엔티티

    @NotNull
    private String name;          // 태그명

    @Builder
    public Tag(String name, List<LogTag> logs) {
        this.name = name;
        this.logs = logs;
    }

}
