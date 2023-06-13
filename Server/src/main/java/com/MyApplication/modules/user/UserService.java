package com.MyApplication.modules.user;

import com.MyApplication.interfaces.IService;
import com.MyApplication.modules.user.dto.CreateAccountDto;
import com.MyApplication.modules.user.entity.User;
import com.MyApplication.modules.user_details.UserDetailsService;
import com.MyApplication.modules.user_details.entity.UserDetails;
import com.MyApplication.utils.HandlePassword;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UserService implements IService<User> {
    private final UserDetailsService userDetailsService = new UserDetailsService();
    @Override
    public void create(User userEntity) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call create_user(?,?,?)}");
            callableStatement.setInt(1,userEntity.getId_user_details());
            callableStatement.setString(2,userEntity.getUsername());
            callableStatement.setString(3, userEntity.getPassword());
            callableStatement.execute();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }
    public void create_account(CreateAccountDto createAccountDto){
        try {
            UserDetails userDetailsEntity = new UserDetails(
                    createAccountDto.getFirstName(),
                    createAccountDto.getLastName(),
                    createAccountDto.getEmail(),
                    createAccountDto.getPhoneNumber(),
                    createAccountDto.getAddress()
            );
            this.userDetailsService.create(userDetailsEntity);
            userDetailsEntity = this.userDetailsService.findByEmail(createAccountDto.getEmail());
            String password = HandlePassword.signIn(createAccountDto.getPassword());
            User userEntity = new User(
                    userDetailsEntity.getId(),
                    createAccountDto.getUsername(),
                    password
            );
            this.create(userEntity);
        } catch (Exception e){
            e.getStackTrace();
        }
    }
    public User findByUsername(String username){
        User userEntity = null;
        try {
            CallableStatement callableStatement =
                    connection.prepareCall("{call findUserByUsername(?,?,?,?)}");
            callableStatement.setString(1,username);
            callableStatement.registerOutParameter(2, Types.BIGINT);
            callableStatement.registerOutParameter(3,Types.BIGINT);
            callableStatement.registerOutParameter(4,Types.VARCHAR);
            callableStatement.execute();
            userEntity = new User(
                    callableStatement.getInt(2),
                    callableStatement.getInt(3),
                    username,
                    callableStatement.getString(4)
            );
        } catch (SQLException e){
            e.getStackTrace();
        }
        return userEntity;
    }
    @Override
    public ArrayList<User> findAll() {
        return null;
    }

    @Override
    public int update(User userEntity) {
        return 0;
    }

    @Override
    public int remove(User userEntity) {
        return 0;
    }

    @Override
    public User findById(int id) {
        return null;
    }

}
