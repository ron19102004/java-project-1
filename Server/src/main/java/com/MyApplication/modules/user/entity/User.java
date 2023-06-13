package com.MyApplication.modules.user.entity;

import lombok.Data;
@Data
public class User {
    private int id;
    private int id_user_details;
    private String username;
    private String password;
    private boolean deleted;
    public User(){}
    public User(int id, int id_user_details, String username, String password) {
        this.id = id;
        this.id_user_details = id_user_details;
        this.username = username;
        this.password = password;
    }
    public User(int id_user_details, String username, String password) {
        this.id_user_details = id_user_details;
        this.username = username;
        this.password = password;
    }
}
