package com.MyApplication;

import com.MyApplication.configuration.DatabaseConfigs;
import com.MyApplication.socket.Server;

public class MyApplication {
    public static void main(String[] args) {
        DatabaseConfigs.getConnection();
        Server server = new Server(3000);
        DatabaseConfigs.closeConnection();
    }
}