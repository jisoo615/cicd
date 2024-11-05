package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.entity.Member;

import static com.haru.doyak.harudoyak.entity.QMember.member;

import com.haru.doyak.harudoyak.repository.querydsl.MemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findMemberByEmail(String email){
        Member getMember = jpaQueryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();
        return Optional.ofNullable(getMember);
    }

}
