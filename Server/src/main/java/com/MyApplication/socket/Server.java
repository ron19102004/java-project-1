package com.MyApplication.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    public static ArrayList<HandleSocket> handleSockets = new ArrayList<>();

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = this.serverSocket.accept();
                HandleSocket handleSocket = new HandleSocket(client);
                handleSockets.add(handleSocket);
                Thread thread = new Thread(handleSocket);
                thread.start();
                System.out.println(client.getPort() + " is running");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
