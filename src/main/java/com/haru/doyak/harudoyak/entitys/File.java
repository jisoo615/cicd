package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String filename;
    @NotNull
    private String originalName;
}
