package com.MyApplication.modules.auth;

import com.MyApplication.modules.auth.dto.SignInDto;
import com.MyApplication.modules.user.UserService;
import com.MyApplication.modules.user.entity.UserEntity;
import com.MyApplication.utils.HandlePassword;

public class AuthService {
    private final UserService userService;
    public AuthService(){
        this.userService = new UserService();
    }
    public UserEntity signIn(SignInDto signInDto){
        UserEntity user = this.userService.findByUsername(signInDto.getUsername());
        if (user.getId_user_details() == 0) return null;
        String passwordDecode = HandlePassword.verify(user.getPassword());
        if (!(passwordDecode.equals(signInDto.getPassword()))) return null;
        return user;
    }
}
