package com.haru.doyak.harudoyak.repository.querydsl;

import com.haru.doyak.harudoyak.entity.Level;

import java.util.Optional;

public interface LevelCustomRepository {
    Optional<Level> findLevelByMemberId(Long memberId);
}
