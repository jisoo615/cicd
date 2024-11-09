package com.haru.doyak.harudoyak.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haru.doyak.harudoyak.entity.File;
import com.haru.doyak.harudoyak.entity.Level;
import com.haru.doyak.harudoyak.entity.Member;
import com.haru.doyak.harudoyak.entity.QMember;
import com.querydsl.core.Tuple;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResDTO {
    @JsonIgnoreProperties({"password", "claims", "doyaks"})
    Member member;
    Level level;
    File file;
}
