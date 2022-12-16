package com.cookos.Controllers;

import com.cookos.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ClientTicketMenuController {


    public TextField questionText;
    public Button backButton;
    public Button submitButton;


    @FXML
    private void initialize() throws IOException {

    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSubmitClickButton(ActionEvent event) throws IOException {
        if (questionText.getText().isBlank()){
            return;
        }
        else{
            Client.ostream.writeObject("ClientWriteTicket");

            Client.ostream.writeObject(questionText.getText());

            onBackButtonClick(null);
        }
    }
}
