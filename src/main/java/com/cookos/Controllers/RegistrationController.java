package com.cookos.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    public ComboBox<String> comboBox;
    @FXML
    private Button RegisterButton;

    @FXML
    private MenuButton cityMenuButton;

    @FXML
    private TextField loginTextBlock;

    @FXML
    private TextField nameTextBlock;

    @FXML
    private TextField numberBlock;

    @FXML
    private TextField passwordTextBlock;

    @FXML
    private TextField surnameTextBlock;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> list = FXCollections.observableArrayList("Minsk","Brest", "Grodno", "Mogilev",
                                                                                "Vitebsk", "Gomel");
        comboBox.setItems(list);
    }

    public void register() {

    }
}
