package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCommentDTO {
    // 댓글 요청 정보를 담을 DTO

    private String commentContent; // 댓글 내용

}
