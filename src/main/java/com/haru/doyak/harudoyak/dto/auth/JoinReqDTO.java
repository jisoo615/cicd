package com.haru.doyak.harudoyak.dto.auth;

import lombok.Data;

@Data
public class JoinReqDTO {
    private String email;
    private boolean isVerified;
    private String password;
    private String nickname;
}
