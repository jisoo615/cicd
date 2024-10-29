package com.haru.doyak.harudoyak.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;
    @NotNull
    private String name;
}
