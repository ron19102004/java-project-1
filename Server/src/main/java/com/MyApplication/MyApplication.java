package com.MyApplication;

import com.MyApplication.configuration.DatabaseConfigs;
import com.MyApplication.socket.Server;

import java.sql.SQLException;

public class MyApplication {
    public static void main(String[] args) {
        try {
            DatabaseConfigs.getConnection();
            new Server(3000);
            DatabaseConfigs.closeConnection();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}