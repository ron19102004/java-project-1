package com.MyApplication.modules.auth.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String username;
    private String password;

    public SignInDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
