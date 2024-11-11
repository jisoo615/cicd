package com.haru.doyak.harudoyak.repository.querydsl;

import com.haru.doyak.harudoyak.dto.sharedoyak.ReqCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ReqShareDoyakDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResReplyCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResShareDoyakDTO;
import com.haru.doyak.harudoyak.entity.Comment;
import com.haru.doyak.harudoyak.entity.ShareDoyak;

import java.util.List;

public interface ShareDoyakCustomRepository {

    long ShaereDoyakDelete(Long shareDoyakId);

    long commentContentUpdate(Long commentId, ReqCommentDTO reqCommentDTO);

    Comment findCommentByMemberId(Long memberId, Long commentId);

    ShareDoyak findShaereDoyakByMemeberId(Long memeberId, Long shareDoyakId);

    List<ResShareDoyakDTO> findeAll();

    List<ResReplyCommentDTO> findeCommentAll(Long shareDoyakId);

    long shareContentUpdate(Long shareDoyakId, ReqShareDoyakDTO reqShareDoyakDTO);

}
