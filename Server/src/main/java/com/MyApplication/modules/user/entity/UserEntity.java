package com.MyApplication.modules.user.entity;

import lombok.Data;
@Data
public class UserEntity {
    private int id;
    private int id_user_details;
    private String username;
    private String password;
    private boolean deleted;
    public UserEntity(){}
    public UserEntity(int id, int id_user_details, String username, String password) {
        this.id = id;
        this.id_user_details = id_user_details;
        this.username = username;
        this.password = password;
    }
    public UserEntity(int id_user_details, String username, String password) {
        this.id_user_details = id_user_details;
        this.username = username;
        this.password = password;
    }
}
