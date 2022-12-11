package com.cookos.Controllers;

import com.cookos.Client;
import com.mysql.cj.protocol.x.XMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class OrderController {
    public ComboBox typeComboBox;
    public ComboBox nameComboBox;
    public ComboBox deliveryComboBox;
    public Button confirmButton;
    public Text priceText;
    public Text cityText;
    public Text surnameText;
    public Text nameText;

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject("EnterOrderMenu");
        var name = (String)Client.istream.readObject();

        if (Objects.equals(name, "UserNotFound"))
            return;

        nameText.setText(name);
        surnameText.setText((String) Client.istream.readObject());
        cityText.setText((String) Client.istream.readObject());


    }

    public void onConfirmButtonClick(ActionEvent event) {

    }

    public void deliveryBox(ActionEvent event) {
    }

    public void typeProductBox(ActionEvent event) {
    }

    public void nameProductBox(ActionEvent event) {
    }
}
