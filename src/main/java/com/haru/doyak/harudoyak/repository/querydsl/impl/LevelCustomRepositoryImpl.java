package com.haru.doyak.harudoyak.repository.querydsl.impl;

import static com.haru.doyak.harudoyak.entity.QLevel.level;

import com.haru.doyak.harudoyak.entity.Level;
import com.haru.doyak.harudoyak.repository.querydsl.LevelCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LevelCustomRepositoryImpl implements LevelCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Level> findLevelByMemberId(Long memberId){
        Level getLevel = jpaQueryFactory.selectFrom(level)
                .where(level.memberId.eq(memberId))
                .fetchOne();
        return Optional.ofNullable(getLevel);
    }
}
