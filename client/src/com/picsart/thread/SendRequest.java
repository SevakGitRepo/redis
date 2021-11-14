package com.picsart.thread;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SendRequest extends Thread {

    @Override
    public void run() {
        System.out.println("Connecting to server");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try (Socket socket = new Socket("127.0.0.1", 8080);
                 //writer@ grelu hamara uxarkuma info
                 BufferedWriter writer = new BufferedWriter(
                         new OutputStreamWriter(socket.getOutputStream()));
                 //reader kardalu hamara stanum e info
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream())
                 )) {
                String request = scanner.nextLine();

                if(request.equals("EXIT"))
                    break;

                writer.write(request);
                writer.newLine();
                writer.flush();

                String response = reader.readLine();
                System.out.println("Response -> " + response);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
