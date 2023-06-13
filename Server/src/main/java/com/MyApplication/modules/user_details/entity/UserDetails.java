package com.MyApplication.modules.user_details.entity;

import lombok.Data;
@Data
public class UserDetails {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean deleted;
    private String phoneNumber;
    private String address;
    public UserDetails(){}
    public UserDetails(int id, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deleted = false;
    }
    public UserDetails(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.deleted = false;
    }
}
