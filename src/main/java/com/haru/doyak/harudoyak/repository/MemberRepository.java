package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.entitys.Member;
import com.haru.doyak.harudoyak.repository.querydsl.MemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    Member findMemberByMemberId(Long memberId);
    boolean existsMemberByMemberId(Long memberId);

}
