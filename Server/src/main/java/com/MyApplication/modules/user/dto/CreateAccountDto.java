package com.MyApplication.modules.user.dto;

import lombok.Data;

@Data
public class CreateAccountDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public CreateAccountDto(String username,
                            String password,
                            String firstName,
                            String lastName,
                            String email,
                            String phoneNumber,
                            String address) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
