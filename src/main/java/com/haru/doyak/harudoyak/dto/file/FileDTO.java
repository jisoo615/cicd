package com.haru.doyak.harudoyak.dto.file;

import lombok.Data;

@Data
public class FileDTO {
    // 파일의 정보를 담는 DTO

    private Long fileId; // 파일 아이디
    private String filePathName; // 파일경로+파일명
    private String originalName; // 업로드시파일명

}
