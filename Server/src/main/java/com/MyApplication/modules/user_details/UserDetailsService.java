package com.MyApplication.modules.user_details;

import com.MyApplication.interfaces.IService;
import com.MyApplication.modules.user_details.entity.UserDetailsEntity;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UserDetailsService implements IService<UserDetailsEntity> {
    @Override
    public int create(UserDetailsEntity userDetailsEntity) {
        try {
            CallableStatement callableStatement =
                    connection.prepareCall("{call create_user_details(?,?,?,?,?)}");
            callableStatement.setString(1,userDetailsEntity.getFirstName());
            callableStatement.setString(2,userDetailsEntity.getLastName());
            callableStatement.setString(3,userDetailsEntity.getPhoneNumber());
            callableStatement.setString(4, userDetailsEntity.getAddress());
            callableStatement.setString(5, userDetailsEntity.getEmail());
            callableStatement.execute();
        } catch (SQLException exception){
            exception.getStackTrace();
            return -1;
        }
        return 0;
    }
    public UserDetailsEntity findByEmail(String email){
        UserDetailsEntity userDetailsEntity = null;
        try {
            CallableStatement callableStatement =
                    connection.prepareCall("{CALL findUserDetailsByEmail(?,?,?,?,?,?)}");
            callableStatement.setString(1,email);
            callableStatement.registerOutParameter(2, Types.BIGINT);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.execute();
            userDetailsEntity = new UserDetailsEntity(
                    callableStatement.getInt(2),
                    callableStatement.getNString(3),
                    callableStatement.getNString(4),
                    email,
                    callableStatement.getNString(5),
                    callableStatement.getNString(6)
            );
        } catch (SQLException e){
            e.getStackTrace();
        }
        return userDetailsEntity;
    }
    @Override
    public ArrayList<UserDetailsEntity> findAll() {
        return null;
    }

    @Override
    public int update(UserDetailsEntity userDetailsEntity) {
        return 0;
    }

    @Override
    public int remove(UserDetailsEntity userDetailsEntity) {
        return 0;
    }

    @Override
    public UserDetailsEntity findById(int id) {
        UserDetailsEntity userDetailsEntity = null;
        try {
            CallableStatement callableStatement =
                    connection.prepareCall("{CALL findUserDetailsById(?,?,?,?,?,?)}");
            callableStatement.setInt(1,id);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.execute();
            userDetailsEntity = new UserDetailsEntity(
                    id,
                    callableStatement.getNString(2),
                    callableStatement.getNString(3),
                    callableStatement.getNString(6),
                    callableStatement.getNString(4),
                    callableStatement.getNString(5)
            );
        } catch (SQLException e){
            e.getStackTrace();
        }
        return userDetailsEntity;
    }
}
