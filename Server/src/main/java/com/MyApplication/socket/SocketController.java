package com.MyApplication.socket;

import com.MyApplication.modules.auth.AuthService;
import com.MyApplication.modules.auth.dto.SignInDto;
import com.MyApplication.modules.user.UserService;
import com.MyApplication.modules.user.entity.UserEntity;
import com.MyApplication.modules.user_details.UserDetailsService;
import com.MyApplication.modules.user_details.entity.UserDetailsEntity;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class SocketController {
    private AuthService authService;
    private UserService userService;
    private UserDetailsService userDetailsService;
    public SocketController(){
        this.authService = new AuthService();
        this.userService = new UserService();
        this.userDetailsService = new UserDetailsService();
    }
    private JSONObject response(String action,String message, Object data){
        JSONObject response = new JSONObject();
        response.put("action",action);
        Map<String,Object> dt = new LinkedHashMap<>();
        dt.put("message",message);
        dt.put("data",data);
        response.put("data",new JSONObject(dt));
        return response;
    }
    public JSONObject handleAction(String action, JSONObject data) {
        JSONObject response = null;
        switch (action){
            case "POST_signIn" -> {
                response = this.signIn(data);
            }
        }
        return response;
    }
    private JSONObject signIn(JSONObject data){
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        SignInDto signInDto = new SignInDto(username,password);
        UserEntity verify = this.authService.signIn(signInDto);
        if (verify == null){
            return this.response(
                    "RES_signIn",
                    "Lỗi đăng nhập ! Tài khoản hoặc mật khẩu không chính xác. Vui lòng kiểm tra lại",
                    null);
        }
        UserDetailsEntity userDetailsEntity = this.userDetailsService.findById(verify.getId_user_details());
        Map<String, Object> userDetails = new LinkedHashMap<>();
        userDetails.put("id",userDetailsEntity.getId());
        userDetails.put("firstName", userDetailsEntity.getFirstName());
        userDetails.put("lastName", userDetailsEntity.getLastName());
        userDetails.put("address", userDetailsEntity.getAddress());
        userDetails.put("phoneNumber", userDetailsEntity.getPhoneNumber());
        userDetails.put("email", userDetailsEntity.getEmail());
        return this.response("RES_signIn","Đăng nhập thành công !", new JSONObject(userDetails));
    }
}
