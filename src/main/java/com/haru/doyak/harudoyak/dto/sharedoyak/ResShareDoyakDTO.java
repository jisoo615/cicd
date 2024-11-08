package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResShareDoyakDTO {

    private String shareAuthorNickname;
    private String goalName;
    private Long shareDoyakId;
    private String shareContent;
    private String shareImageUrl;
    private Long commentCount;
    private Long doyakCount;
    private List<ResCommentDTO> resComments;


}
