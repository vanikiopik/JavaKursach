package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Utilits.HashPassword;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.ref.Cleaner;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {


    public ComboBox<String> comboBox;
    public Button backButton;
    public Text wrongInputText;
    @FXML
    private Button RegisterButton;


    @FXML
    private TextField loginTextBlock;

    @FXML
    private TextField nameTextBlock;


    @FXML
    private TextField passwordTextBlock;

    @FXML
    private TextField surnameTextBlock;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        wrongInputText.setVisible(false);
        ObservableList<String> list = FXCollections.observableArrayList("Minsk","Brest", "Grodno", "Mogilev",
                                                                                "Vitebsk", "Gomel");
        comboBox.setItems(list);
        comboBox.setValue("None");
    }

    public void register() throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        String city = comboBox.getSelectionModel().getSelectedItem().toString();
        if(loginTextBlock.getText().isBlank() | passwordTextBlock.getText().isBlank() | Objects.equals(city, "City")) {
            wrongInputText.setVisible(true);
        }
        else{
            Client.ostream.writeObject("RegisterAttempt");
            Client.ostream.flush();

            //Checking login for unique
            Client.ostream.writeObject(loginTextBlock.getText());
            Client.ostream.flush();
            var CallbackMessage =  (String) Client.istream.readObject();
            if (Objects.equals(CallbackMessage, "LoginUsed")) {
                System.out.println(CallbackMessage);
                System.out.println("LoginUsed");
                return;
            }


            Client.ostream.writeObject(nameTextBlock.getText());
            Client.ostream.flush();
            Client.ostream.writeObject(surnameTextBlock.getText());
            Client.ostream.flush();
            Client.ostream.writeObject(comboBox.getValue());
            Client.ostream.flush();
            Client.ostream.writeObject(HashPassword.getHash(passwordTextBlock.getText()));
            Client.ostream.flush();
        }

    }

    public void returnToLoginMenu() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Authorization.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
