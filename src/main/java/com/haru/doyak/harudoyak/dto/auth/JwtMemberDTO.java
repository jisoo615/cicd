package com.haru.doyak.harudoyak.dto.auth;

import com.haru.doyak.harudoyak.dto.jwt.JwtRecord;
import com.haru.doyak.harudoyak.entity.Member;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtMemberDTO {
    JwtRecord jwtRecord;
    Member member;
}
