package com.haru.doyak.harudoyak.dto.auth;

import lombok.Data;

@Data
public class LoginReqDTO {
    private String email;
    private String password;
}
