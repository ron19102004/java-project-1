package com.MyApplication.socket;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class HandleSocket implements Runnable{
    private int port;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private SocketController socketController;
    public HandleSocket(Socket socket){
        this.socket = socket;
        this.port = socket.getPort();
        this.socketController = new SocketController();
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getPort(){return this.port;}
    public void close(){
        try {
            this.socket.close();
            this.bufferedWriter.close();
            this.bufferedReader.close();
            Server.handleSockets.removeIf(handleSocket -> handleSocket.port == this.getPort());
        } catch (IOException e){
            e.getStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            String request;
            while ((request = this.bufferedReader.readLine()) != null){
                JSONObject resObj = new JSONObject(request);
                String action = resObj.getString("action");
                JSONObject data = (JSONObject) resObj.get("data");
                JSONObject response = this.socketController.handleAction(action,data);
                this.bufferedWriter.write(response.toString());
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
        } catch (IOException exception){
            exception.getStackTrace();
            this.close();
        }
    }
}
