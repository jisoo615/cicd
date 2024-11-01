package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.entitys.Log;
import com.haru.doyak.harudoyak.repository.querydsl.LogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> , LogCustomRepository {
}
