package com.cookos.Controllers;

import com.cookos.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DeliveryController {

    public Text delieveryPrice;
    public ComboBox deliveryCityComboBox;
    public Button backButton;

    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList("Minsk","Brest", "Grodno", "Mogilev",
                "Vitebsk", "Gomel");
        deliveryCityComboBox.setItems(list);
        Client.ostream.writeObject("EnterDeliveryWindow");
        //Getting city from server
        var message = (String)Client.istream.readObject();
        if (Objects.equals(message, "UserNotFound"))
            return;

        deliveryCityComboBox.setValue("Your city is " + message.toUpperCase());

    }

    public void onSelectedValue() {
        var choiceCity = deliveryCityComboBox.getSelectionModel().getSelectedItem().toString();
        if (Objects.equals(choiceCity, "Minsk"))
            delieveryPrice.setText("10$");
        if (Objects.equals(choiceCity, "Brest"))
            delieveryPrice.setText("50$");
        if (Objects.equals(choiceCity, "Vitebsk"))
            delieveryPrice.setText("50$");
        if (Objects.equals(choiceCity, "Grondo"))
            delieveryPrice.setText("70$");
        if (Objects.equals(choiceCity, "Gomel"))
            delieveryPrice.setText("70$");
        if (Objects.equals(choiceCity, "Mogilev"))
            delieveryPrice.setText("100$");
        }
    }
