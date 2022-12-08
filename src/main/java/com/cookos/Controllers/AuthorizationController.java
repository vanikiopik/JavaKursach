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

    private String answer = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

/*    @FXML
    void initialize() {

        RegisterButton.setOnAction(event -> {
            RegisterButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/cookos/Registration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        EnterDataButton.setOnAction(event->
        {
            String authorLogin = loginField.getText().trim();
            String authorPassword = passwordField.getText().trim();

            if (!authorLogin.equals("root") | !authorPassword.equals("root")) {
                loginUser(authorLogin, authorPassword);

            } else {
                System.out.println("Необходимо заполнить поля");
            }
        });
    }*/

    @FXML
    void initialize(){
        wrongInputText.setVisible(false);
    }



    public void submit() throws IOException, NoSuchAlgorithmException {
        Client.ostream.writeObject("LoginAttempt");
        Client.ostream.flush();

        Client.ostream.writeObject(loginField.getText());
        Client.ostream.flush();

        var hash = HashPassword.getHash(passwordField.getText());
        Client.ostream.writeInt(hash.length);
        Client.ostream.flush();
        Client.ostream.write(hash, 0, hash.length);
        Client.ostream.flush();

        new Thread(() ->{
            try {
                answer = (String)Client.istream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                return;
            }

            if(Objects.equals(answer, "OK")){
                Platform.runLater(() ->{
                    try {
                        FXMLAdditional.setRoot("Registration");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            wrongInputText.setVisible(true);
            Platform.runLater(() ->{
                System.out.println("Try again");
            });
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
}
