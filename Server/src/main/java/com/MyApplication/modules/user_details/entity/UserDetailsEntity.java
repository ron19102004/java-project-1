package com.MyApplication.modules.user_details.entity;

import lombok.Data;
@Data
public class UserDetailsEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean deleted;
    private String phoneNumber;
    private String address;
    public UserDetailsEntity(){}
    public UserDetailsEntity(int id,String firstName, String lastName, String email, String phoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deleted = false;
    }
    public UserDetailsEntity(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deleted = false;
    }
}
