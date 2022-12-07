package com.cookos;

import com.cookos.Entities.User;
import com.cookos.Patterns.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

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
        try {
            handleLogin();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void handleLogin() throws Exception {
        while (true){
            var login = (String)istream.readObject();

            int hashLength = istream.readInt();
            var password = new byte[hashLength];
            istream.readFully(password);

            try (var userDao = new DAO<>(User.class)) {
                var user = userDao.findByColumn("login", login);

                if(user == null){
                    ostream.writeObject("wrong");
                    System.out.println("WrongL");
                    ostream.flush();

                    continue;
                }
                if(!Arrays.equals(password, user.getPassword())){
                    ostream.writeObject("wrong");
                    System.out.println("WrongP");
                    ostream.flush();

                    continue;
                }
                ostream.writeObject("OK");
                System.out.println("OK");
                ostream.flush();
            }
            break;
        }
    }
}
