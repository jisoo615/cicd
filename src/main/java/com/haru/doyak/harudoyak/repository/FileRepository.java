package com.haru.doyak.harudoyak.repository;

import com.haru.doyak.harudoyak.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    File findByFileId(Long fileId);
    boolean existsFileByFileId(Long fileId);

}
