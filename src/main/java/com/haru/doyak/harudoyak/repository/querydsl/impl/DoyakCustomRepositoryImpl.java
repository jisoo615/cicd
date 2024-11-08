package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.repository.querydsl.DoyakCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.haru.doyak.harudoyak.entity.QDoyak.doyak;

@Repository
@RequiredArgsConstructor
public class DoyakCustomRepositoryImpl implements DoyakCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existsByMemberId(Long memberId) {
        boolean existsByMemberId = jpaQueryFactory
                .selectFrom(doyak)
                .where(doyak.member.memberId.eq(memberId))
                .fetchFirst() != null;
        return existsByMemberId;
    }

    /*
     * 도약 삭제 : 해당 아이디의 도약수 삭제
     * req : memberId(Long)
     * */
    @Override
    public Long deleteDoyakByMemberId(Long memberId) {
        Long deleteDoyakByMemberId = jpaQueryFactory
                .delete(doyak)
                .where(doyak.member.memberId.eq(memberId))
                .execute();
        return deleteDoyakByMemberId;
    }

    /*
     * 해당 서로도약의 총 도약수
     * */
    @Override
    public Long findDoyakAllCount() {
        Long doyakCount = jpaQueryFactory
                .select(doyak.member.count().as("doyakCount"))
                .from(doyak)
                .groupBy(doyak.member)
                .fetchCount();
        return doyakCount;
    }
}
