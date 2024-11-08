package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResReplyCommentDTO {

    private Long commentShareDoyakId;     // 댓글의 서로도약ID
    private Long commentId;               // 댓글ID
    private String commentContent;        // 댓글의 글내용
    private String commentAuthorNickname; // 댓글 작성자 닉네임
    private Long replyCommentCount;       // 댓글의 답글 총 수
    private List<Long> replyCommentIds;   // 댓글의 답글 목록

}