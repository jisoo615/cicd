package com.haru.doyak.harudoyak.dto.auth;

import com.haru.doyak.harudoyak.entity.Member;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResDTO {
    String refreshToken;
    Long memberId;
    String nickname;
    String aiNickname;
    String goalName;
    String email;
    boolean isVerified;

}
