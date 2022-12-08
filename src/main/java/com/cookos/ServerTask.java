package com.cookos;

import com.cookos.Entities.User;
import com.cookos.Patterns.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;

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
        while(true){
        try {
            if (Objects.equals(commandListener(), "LoginAttempt")) {
                handleLogin();
            }
            else if (Objects.equals(commandListener(), "RegisterAttempt")) {
                handleRegister();
            }
            else
                System.out.println("Listener Error");
        } catch (Exception e) {
            e.printStackTrace();
            return;
            }
        }
    }

    String commandListener() throws IOException, ClassNotFoundException {
        var choicer = (String)istream.readObject();
        System.out.println(choicer);
        return choicer;
    }

    private void handleRegister() throws Exception {
        var login = (String)istream.readObject();

        try (var userDao = new DAO<>(User.class)) {
            var user = userDao.findByColumn("login", login);

            if (user == null) {
                ostream.writeObject("LoginUsed");
                System.out.println("LoginUsed");
                ostream.flush();
                return;
            }
        }
        var userName = (String)istream.readObject();
        var userSurname = (String)istream.readObject();
        var userCity = (String)istream.readObject();
        byte[] password = (byte[]) istream.readObject();
        final int isAdmin = 0;


        var user = new User(1,login, password, userName, userSurname, userCity, isAdmin);
        try (var userDao = new DAO<>(User.class)) {
            userDao.add(user);
        }
    }

    private void handleLogin() throws Exception {
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
                    return;
                }

                if(!Arrays.equals(password, user.getPassword())){
                    ostream.writeObject("wrong");
                    System.out.println("WrongP");
                    ostream.flush();
                    return;
                }
                ostream.writeObject("OK");
                System.out.println("OK");
                ostream.flush();
            }
    }
}
