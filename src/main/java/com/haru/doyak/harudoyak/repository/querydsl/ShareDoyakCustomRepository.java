package com.haru.doyak.harudoyak.repository.querydsl;

import com.haru.doyak.harudoyak.dto.sharedoyak.ResReplyCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResShareDoyakDTO;

import java.util.List;

public interface ShareDoyakCustomRepository {

List<ResShareDoyakDTO> findeAll();

List<ResReplyCommentDTO> findeCommentAll(Long shareDoyakId);

}
