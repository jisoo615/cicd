package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.entitys.Member;
import com.haru.doyak.harudoyak.entitys.QMember;

import com.haru.doyak.harudoyak.repository.querydsl.MemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    private final QMember m = QMember.member;

    @Override
    public Optional<Member> findMemberByEmail(String email){
        Member member = jpaQueryFactory.selectFrom(m)
                .where(m.email.eq(email))
                .fetchOne();
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> saveMember(Member member) {
        entityManager.persist(member);
        return Optional.ofNullable(member);
    }

}
