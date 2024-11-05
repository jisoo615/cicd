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

    public void convertToLoginResDTO(JwtMemberDTO dto) {
        this.memberId = dto.jwtRecord.memberId();
        this.nickname = dto.getMember().getNickname();
        this.aiNickname = dto.getMember().getAiNickname();
        this.goalName = dto.getMember().getGoalName();
        this.email = dto.getMember().getEmail();
        this.isVerified = dto.getMember().getIsVerified();
        this.refreshToken = dto.jwtRecord.refreshToken();
    }
}
