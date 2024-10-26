package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.domain.Member;
import com.haru.doyak.harudoyak.repository.querydsl.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
