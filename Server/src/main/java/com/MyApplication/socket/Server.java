package com.MyApplication.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<HandleSocket> handleSockets = new ArrayList<>();

    public Server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (true) {
                Socket client = serverSocket.accept();
                HandleSocket handleSocket = new HandleSocket(client);
                handleSockets.add(handleSocket);
                Thread thread = new Thread(handleSocket);
                thread.start();
                System.out.println(client.getPort() + " is running");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
