package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqShareDoyakDTO {
    // 서로도약 요청 정보를 받는 DTO

    private String shareContent;
    private String shareImegeUrl; // 서로도약 이미지파일 url
//    private String shareFileOriginalName; // 서로도약 업로드시 파일명(.확장자포함)

}
