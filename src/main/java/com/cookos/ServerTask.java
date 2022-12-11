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
        String listener;
        while(true){
            try {
                listener = commandListener();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
            if (Objects.equals(listener, "LoginAttempt")) {
                handleLogin();
            }
            else if (Objects.equals(listener, "RegisterAttempt")) {
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

            if (user != null) {
                ostream.writeObject("LoginUsed");
                System.out.println("LoginUsed");
                ostream.flush();
                return;
            }
            else
                ostream.writeObject("LoginFree");
        }

        var userName = (String)istream.readObject();
        var userSurname = (String)istream.readObject();
        var userCity = (String)istream.readObject();
        byte[] password = (byte[]) istream.readObject();
        final int isAdmin = 0;



        var user = new User(login, password, userName, userSurname, userCity, isAdmin);
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
                ostream.flush();
                System.out.println("WrongP");
                return;
            }

            if(user.getIsAdmin() == 0){
                ostream.writeObject("OK");
                System.out.println("OK");
                ostream.flush();
            }
            else{
                ostream.writeObject("OK_a");
                System.out.println("OK_a");
                ostream.flush();
            }

        }
    }


}
