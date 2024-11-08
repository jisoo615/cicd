package com.haru.doyak.harudoyak.repository.querydsl;

public interface DoyakCustomRepository {

    Long findDoyakAllCount();

    Long deleteDoyakByMemberId(Long memberId);

    boolean existsByMemberId(Long memberId);

}
