package com.cookos.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController {

    public Button EnterDataButton;
    public Button RegisterButton;
    public TextField loginField;
    public TextField passwordField;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

        RegisterButton.setOnAction(event -> {
            RegisterButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/kursa/Registration.fxml"));

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
    }




    private void loginUser(String authorLogin, String authorPassword) {
    }
}
