package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResCommentDTO {

    private Long commentShareDoyakId;
    private Long commentId;
    private String commentContent;
    private String commentAuthorNickname;

}
