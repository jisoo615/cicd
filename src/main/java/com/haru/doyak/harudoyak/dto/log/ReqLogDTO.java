package com.haru.doyak.harudoyak.dto.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqLogDTO {
    // 도약기록 요청 정보를 받는 DTO

    private String content;    // 도약기록 내용
    private String [] tagName; // 태그명 배열
    private String emotion;    // 오늘의 감정

}