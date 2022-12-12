package com.cookos;

import com.cookos.Entities.Shop;
import com.cookos.Entities.User;
import com.cookos.Patterns.DAO;
import com.cookos.Utilits.CatalogTask;
import com.cookos.Utilits.OrderTask;
import jakarta.persistence.criteria.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ServerTask implements  Runnable {

    private ObjectOutputStream ostream;
    private ObjectInputStream istream;

    private int userId = 0;

    public ServerTask(Socket socket) throws IOException {
        ostream = new ObjectOutputStream(socket.getOutputStream());
        ostream.flush();
        istream = new ObjectInputStream(socket.getInputStream());
    }


    @Override
    public void run() {
        String listener;
        while (true) {
            try {
                listener = commandListener();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                //Try when login
                if (Objects.equals(listener, "LoginAttempt")) {
                    handleLogin();
                }
                //Try to register
                else if (Objects.equals(listener, "RegisterAttempt")) {
                    handleRegister();
                }
                //Enter to catalog panel in ClientMenu
                else if (Objects.equals(listener, "EnteringCatalog")) {
                    System.out.println("EnterCatalog");
                    fillShopTables();
                }
                //User enter delivery window
                else if (Objects.equals(listener, "EnterDeliveryWindow")) {
                    operateDelivery();
                }
                //Opens window to make order
                else if (Objects.equals(listener, "EnterOrderMenu")) {
                    operateOrder();

                } else if (Objects.equals(listener, "PlaceOrder")) {
                    placeOrder();
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
        var choicer = (String) istream.readObject();
        System.out.println(choicer);
        return choicer;
    }

    private void handleRegister() throws Exception {
        var login = (String) istream.readObject();

        try (var userDao = new DAO<>(User.class)) {
            var user = userDao.findByColumn("login", login);

            if (user != null) {
                ostream.writeObject("LoginUsed");
                System.out.println("LoginUsed");
                ostream.flush();
                return;
            } else
                ostream.writeObject("LoginFree");
        }

        var userName = (String) istream.readObject();
        var userSurname = (String) istream.readObject();
        var userCity = (String) istream.readObject();
        byte[] password = (byte[]) istream.readObject();
        final int isAdmin = 0;


        var user = new User(login, password, userName, userSurname, userCity, isAdmin);
        try (var userDao = new DAO<>(User.class)) {
            userDao.add(user);
        }
    }

    private void handleLogin() throws Exception {
        userId = -1;
        var login = (String) istream.readObject();

        int hashLength = istream.readInt();
        var password = new byte[hashLength];
        istream.readFully(password);

        try (var userDao = new DAO<>(User.class)) {
            var user = userDao.findByColumn("login", login);

            if (user == null) {
                ostream.writeObject("wrong");
                System.out.println("WrongL");
                ostream.flush();
                return;
            }

            if (!Arrays.equals(password, user.getPassword())) {
                ostream.writeObject("wrong");
                ostream.flush();
                System.out.println("WrongP");
                return;
            }

            if (user.getIsAdmin() == 0) {
                userId = user.getUserID();
                ostream.writeObject("OK");
                System.out.println("OK");
                ostream.flush();
            } else {
                ostream.writeObject("OK_a");
                System.out.println("OK_a");
                ostream.flush();
            }

        }
    }

    private void fillShopTables() throws SQLException, IOException, ClassNotFoundException {
        CatalogTask catalogTask = new CatalogTask();
        var shopList = (List<Shop>) catalogTask.getTable();
        ostream.writeObject(shopList);
    }

    private void operateDelivery() throws Exception {
        try (var userDao = new DAO<>(User.class)) {
            var user = userDao.findByColumn("userID", userId);

            if (user != null) {
                ostream.writeObject(user.getUserCity());
                ostream.flush();
                return;
            } else
                ostream.writeObject("UserNotFound");

        }
    }

    private void operateOrder() throws Exception {
        OrderTask orderTask = new OrderTask();
        try (var userDao = new DAO<>(User.class)) {
            var user = userDao.findByColumn("userID", userId);
            if (user != null) {

                ostream.writeObject(user.getUserName());
                ostream.flush();
                ostream.writeObject(user.getUserSurname());
                ostream.flush();
                ostream.writeObject(user.getUserCity());
                ostream.flush();

                var shopList = orderTask.getAvailableProduct();
                ostream.writeObject(shopList);


                return;
            } else
                ostream.writeObject("UserNotFound");
        }
    }

    private void placeOrder() throws IOException, ClassNotFoundException {
        var productName = (String)istream.readObject();
        var finalPrice = (Float)istream.readObject();
        var deliveryStatus = (String)istream.readObject();
        System.out.println(productName);
        System.out.println(finalPrice);
        System.out.println(deliveryStatus);

    }
}