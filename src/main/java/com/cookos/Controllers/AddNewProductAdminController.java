package com.cookos.Controllers;

import com.cookos.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddNewProductAdminController {

    public Button backButton;
    public Button addButton;
    public TextField nameField;
    public TextField typeField;
    public TextField priceField;
    public TextField amountField;

    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onAddButtonClick(ActionEvent event) throws IOException {
        if (nameField.getText().isBlank() |
                typeField.getText().isBlank() |
                priceField.getText().isBlank() |
                amountField.getText().isBlank())
        {
            return;
        }
        else{
            Client.ostream.writeObject("AddingNewProduct");

            Client.ostream.writeObject(nameField.getText());
            Client.ostream.flush();
            Client.ostream.writeObject(typeField.getText());
            Client.ostream.flush();
            Client.ostream.writeObject(Float.valueOf(priceField.getText()));
            Client.ostream.flush();
            Client.ostream.writeObject(Integer.valueOf(amountField.getText()));
        }
    }
}

