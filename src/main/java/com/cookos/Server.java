package com.cookos;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {


    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(1234)) {
            while (true){

                var socket = serverSocket.accept();

                System.out.println("Connected: " + socket.getInetAddress() + socket.getPort());

                var thread = new Thread(new ServerTask(socket));
                thread.start();
            }
        }
    }
}
