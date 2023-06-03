package com.MyApplication.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HandlePassword {
    public static String signIn(String password){
        byte[] passwordByte = password.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(passwordByte);
    }
    public static String verify(String password){
        byte[] pw = Base64.getDecoder().decode(password);
        return new String(pw);
    }
}
