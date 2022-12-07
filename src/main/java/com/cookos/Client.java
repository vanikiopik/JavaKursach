package com.cookos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client extends Application {
    public static Socket socket = null;

    public static ObjectOutputStream ostream = null;
    public static ObjectInputStream istream = null;
    public static Scene scene = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("Authorization.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        var loadingThread = new Thread(() -> {
            try{
                Client.socket = new Socket("127.0.0.1", 1234);
                Client.ostream = new ObjectOutputStream(Client.socket.getOutputStream());
                Client.ostream.flush();
                Client.istream = new ObjectInputStream(Client.socket.getInputStream());
            } catch (IOException e){
                e.printStackTrace();
            }
        });
        loadingThread.start();

    }

    public static void main(String[] args) {
        launch();
    }
}