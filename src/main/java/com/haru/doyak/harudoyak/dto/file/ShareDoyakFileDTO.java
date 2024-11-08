package com.haru.doyak.harudoyak.dto.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShareDoyakFileDTO extends FileDTO {
    // 서로도약 파일 정보를 전달하는 DTO

    private String shareImegeUrl; // 서로도약 이미지파일 url
    private String shareFileOriginalName; // 서로도약 업로드시 파일명(.확장자포함)

    public ShareDoyakFileDTO (String shareImegeUrl, String shareOriginalName) {
        super.setFilePathName(shareImegeUrl);
        super.setOriginalName(shareFileOriginalName);
    }

}
