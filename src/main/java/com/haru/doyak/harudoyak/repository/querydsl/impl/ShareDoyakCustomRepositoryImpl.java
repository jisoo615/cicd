package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.entitys.QShareDoyak;
import com.haru.doyak.harudoyak.repository.querydsl.ShareDoyakCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShareDoyakCustomRepositoryImpl implements ShareDoyakCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private final QShareDoyak shareDoyak= QShareDoyak.shareDoyak;



}
