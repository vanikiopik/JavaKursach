package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Utilits.FXMLAdditional;
import com.cookos.Utilits.HashPassword;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthorizationController {

    public Button EnterDataButton;
    public Button RegisterButton;
    public TextField loginField;
    public TextField passwordField;
    public Text wrongInputText;
    public Button aboutApplicationButton;

    private String answer = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize(){
        wrongInputText.setVisible(false);
    }


    public void change_on_client_window(int i) throws IOException, NoSuchAlgorithmException {

        Stage stage;
        Parent root = null;
        stage = (Stage) EnterDataButton.getScene().getWindow();
        if(i == 0) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientMenu.fxml")));
        }
        else if (i == 1){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminMenu.fxml")));
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

        public void submit() throws IOException, NoSuchAlgorithmException {
            System.out.println("submit");
            Client.ostream.writeObject("LoginAttempt");
            Client.ostream.flush();
            Client.ostream.writeObject(loginField.getText().trim());
            Client.ostream.flush();

            var hash = HashPassword.getHash(passwordField.getText().trim());
            Client.ostream.writeInt(hash.length);
            Client.ostream.flush();
            Client.ostream.write(hash, 0, hash.length);
            Client.ostream.flush();

            new Thread(() ->{
                try {
                    answer = (String)Client.istream.readObject();
                    System.out.println("answer = " + answer);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    return;
                }

                if(Objects.equals(answer, "OK")){
                    Platform.runLater(() ->{
                        try {
                            change_on_client_window(0);
                        } catch (IOException | NoSuchAlgorithmException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                else if(Objects.equals(answer, "OK_a")){
                    Platform.runLater(() ->{
                        try {
                            change_on_client_window(1);
                        } catch (IOException | NoSuchAlgorithmException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                wrongInputText.setVisible(true);
            }).start();
        }

    public void registerButton() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) RegisterButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Registration.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAboutApplicationButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) aboutApplicationButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/InfoAboutDevelopersWindow.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
