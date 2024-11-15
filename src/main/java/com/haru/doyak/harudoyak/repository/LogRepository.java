package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.entity.Log;
import com.haru.doyak.harudoyak.repository.querydsl.LogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> , LogCustomRepository {

    Log findLogByLogId(Long logId);

    boolean existsByLogId(Long logId);

}
