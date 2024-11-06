package com.haru.doyak.harudoyak.dto.sharedoyak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDoyakDTO {
    // 도약 응답 정보를 담는 DTO

    private Long memberId;  // 회원 아이디
    private Long doyakCount;// 해당 게시글의 총 도약수

}
