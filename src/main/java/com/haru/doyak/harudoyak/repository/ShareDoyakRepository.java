package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.entity.ShareDoyak;
import com.haru.doyak.harudoyak.repository.querydsl.ShareDoyakCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareDoyakRepository extends JpaRepository<ShareDoyak, Long>, ShareDoyakCustomRepository {

    ShareDoyak findShareDoyakByMemberId(Long memberId);
    ShareDoyak findShareDoyakByShareDoyakId(Long shareDoyakId);
    boolean existsByshareDoyakId(Long shareDoyakId);

}
