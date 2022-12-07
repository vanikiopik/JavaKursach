package com.cookos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerTask implements  Runnable{

    private ObjectOutputStream ostream;
    private ObjectInputStream istream;

    public ServerTask(Socket socket) throws IOException{
        ostream = new ObjectOutputStream(socket.getOutputStream());
        ostream.flush();
        istream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

    }
}
