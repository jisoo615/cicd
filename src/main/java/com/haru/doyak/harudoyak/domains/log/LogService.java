package com.haru.doyak.harudoyak.domains.log;

import com.haru.doyak.harudoyak.dto.log.ReqLogDTO;
import com.haru.doyak.harudoyak.entitys.Log;
import com.haru.doyak.harudoyak.repository.LogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void setLogAdd(ReqLogDTO reqLogDTO, Long memberId){

        // 도약기록 추가 전 회원 조회
        // Boolean isExistsMember = existsByMemberId(memberId);



        // if (){}
        Log log = Log.builder()
//                .member(member)
                .content(reqLogDTO.getContent())
                .emotion(reqLogDTO.getEmotion())
                .build();
        entityManager.persist(log);
    }

}
