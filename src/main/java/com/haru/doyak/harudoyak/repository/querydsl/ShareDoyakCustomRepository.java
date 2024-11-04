package com.haru.doyak.harudoyak.repository.querydsl;

public interface ShareDoyakCustomRepository {

    Long findDoyakAllCount();

    Long deleteDoyakByMemberId(Long memberId);

    boolean existsByMemberId(Long memberId);

}
