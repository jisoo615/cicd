package com.haru.doyak.harudoyak.repository.querydsl.impl;

import com.haru.doyak.harudoyak.entitys.Member;

import static com.haru.doyak.harudoyak.entitys.QMember.member;
import com.haru.doyak.harudoyak.repository.MemberRepository;
import com.haru.doyak.harudoyak.repository.querydsl.MemberCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final MemberRepository memberRepository;

    // static private final QMember m = QMember.member;

    @Override
    public Optional<Member> findMemberByEmail(String email){
        Member getMember = jpaQueryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();
        return Optional.ofNullable(getMember);
    }

    @Override
    public Optional<Member> saveMember(Member getMember) {
        return memberRepository.saveMember(getMember);
    }

}
