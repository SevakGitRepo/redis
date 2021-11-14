package com.picsart.handler;

import com.picsart.redis.Redis;
import com.picsart.validator.StringValidator;

import java.io.*;
import java.net.Socket;

public class Handler extends Thread {

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream())
             )) {
            String request = reader.readLine();
            System.out.println("Request -> " + request);

            String response ="Command is invalid";
            if(StringValidator.isValid(request)) {
                response = Redis.action(request);
            }

            writer.write(response);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
