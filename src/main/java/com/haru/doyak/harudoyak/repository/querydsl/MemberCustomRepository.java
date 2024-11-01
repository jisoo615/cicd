package com.haru.doyak.harudoyak.repository.querydsl;

import com.haru.doyak.harudoyak.entitys.Member;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> saveMember(Member member);
}
