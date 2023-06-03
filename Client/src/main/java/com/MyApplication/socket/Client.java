package com.MyApplication.socket;

import com.MyApplication.entities.IUser;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class Client implements Runnable {

    private static IUser iUser;
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedWriter writer;

    public Client(String host, int port) throws IOException {
        this.iUser = null;
        this.socket = new Socket(host, port);
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
    }

    public static void close() {
        try {
            socket.close();
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONObject request(String action, Object data) {
        JSONObject request = new JSONObject();
        request.put("action", action);
        request.put("data", data);
        return request;
    }

    @Override
    public void run() {
        try {
            String response;
            while ((response = this.reader.readLine()) != null) {
                JSONObject res = new JSONObject(response);
                String action = res.getString("action");
                System.out.println(res);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void signIn(String username, String password) {
        try {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("username", username);
            data.put("password", password);
            writer.write(request("POST_signIn", new JSONObject(data)).toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.getStackTrace();
            Client.close();
        }
    }

}
