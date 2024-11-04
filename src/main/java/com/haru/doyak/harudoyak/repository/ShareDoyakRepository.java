package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.entitys.ShareDoyak;
import com.haru.doyak.harudoyak.repository.querydsl.ShareDoyakCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareDoyakRepository extends JpaRepository<ShareDoyak, Long>, ShareDoyakCustomRepository {

    ShareDoyak findShareDoyakByShareDoyakId(Long shareDoyakId);
    boolean existsByshareDoyakId(Long shareDoyakId);

}
