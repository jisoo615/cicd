package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqShareDoyakDTO {
    // 서로도약 요청 정보를 받는 DTO

    private String content;

}
