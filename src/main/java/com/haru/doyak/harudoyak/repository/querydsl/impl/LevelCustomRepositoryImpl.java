package com.haru.doyak.harudoyak.repository.querydsl.impl;

import static com.haru.doyak.harudoyak.entity.QLevel.level;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LevelCustomRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;
}
