package com.MyApplication.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfigs {
    public static Connection connection;
    public static void getConnection(){
        DatabaseConfigs.connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String username = "root";
            String password = "dung28011951";
            String host = "localhost";
            int port = 3306;
            String database = "java_project2023";
            String url = "jdbc:mysql://"+host+":"+port+"/"+database+"?useSSL=false";
            DatabaseConfigs.connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException sqlException){
            sqlException.getStackTrace();
        }
    }
    public static void closeConnection(){
        try {
            if (DatabaseConfigs.connection != null) DatabaseConfigs.connection.close();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }
}
