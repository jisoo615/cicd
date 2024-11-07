package com.haru.doyak.harudoyak.repository.querydsl;

import com.haru.doyak.harudoyak.entity.Member;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberByNickname(String phone);
    Optional<Member> findMemberById(Long id);
    Optional<Member> findMemberByRefreshToken(String refreshToken);
    Optional<Member> findMemberByGoogleId(String googleId);
    Optional<Member> findMemberByKakaoId(String kakaoId);
}
