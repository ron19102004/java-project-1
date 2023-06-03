package com.MyApplication;

import com.MyApplication.socket.Client;
import com.MyApplication.views.auth.LoginView;

import java.io.IOException;
import javax.swing.UIManager;

public class MyApplication {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Client client = new Client("localhost",3000);
            Thread task = new Thread(client);
            task.start();
            new LoginView();
        } catch (Exception ex){
            ex.getStackTrace();
        }
    }

}