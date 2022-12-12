package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Entities.Shop;
import com.cookos.Entities.User;
import com.cookos.Patterns.DAO;
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
import java.util.List;
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
    public Text typeText;
    public Button backButton;
    public Text inputErrorText;

    private ObservableList<Shop> shopList = FXCollections.observableArrayList();

    ObservableList<String> nameProductList = FXCollections.observableArrayList();
    ObservableList<String> typeProductList = FXCollections.observableArrayList();
    ObservableList<Float> priceProductList = FXCollections.observableArrayList();

    ObservableList<String> deliveryList = FXCollections.observableArrayList("Yes" , "No");

    private float finalPrice;
    private String name;
    private String deliveryStatus;

    private float delieveryPrice = 0;
    private float productPrice = 0;


    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        inputErrorText.setVisible(false);
        Client.ostream.writeObject("EnterOrderMenu");

        deliveryComboBox.setItems(deliveryList);
        deliveryComboBox.setValue("Choose");

        nameComboBox.setValue("Choose");

        var name = (String)Client.istream.readObject();
        if (Objects.equals(name, "UserNotFound"))
            return;

        //Setting info about client
        nameText.setText(name);
        surnameText.setText((String) Client.istream.readObject());
        var city = (String) Client.istream.readObject();
        cityText.setText(city);


        shopList.addAll((List<Shop>) Client.istream.readObject());

        for (Shop shop : shopList){
            nameProductList.add(shop.getProductName());
            typeProductList.add(shop.getProductType());
            priceProductList.add(shop.getPrice());
        }
        nameComboBox.setItems(nameProductList);
        typeComboBox.setItems(typeProductList);


    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        if(Objects.equals(nameComboBox.getValue(), "Choose") |
                Objects.equals(typeComboBox.getValue(), "Choose"))
        {
            inputErrorText.setVisible(true);
        }
        else{
            inputErrorText.setVisible(false);
            Client.ostream.writeObject("PlaceOrder");

            name = nameComboBox.getValue().toString();
            deliveryStatus = deliveryComboBox.getValue().toString(); // Yes | No
            //Sending to server name of product,finalPrice, Order status(Yes\No)

            Client.ostream.writeObject(name);
            Client.ostream.flush();
            Client.ostream.writeObject(finalPrice);
            Client.ostream.flush();
            Client.ostream.writeObject(deliveryStatus);
            Client.ostream.flush();


        }
    }

    public void deliveryBox(ActionEvent event) {
        var deliveryChoice = deliveryComboBox.getSelectionModel().getSelectedItem().toString();
        if (Objects.equals(deliveryChoice, "Yes")) {
            if (Objects.equals(cityText.getText(), "Minsk"))
                delieveryPrice = 10;
            else if (Objects.equals(cityText.getText(), "Brest"))
                delieveryPrice = 50;
            else if (Objects.equals(cityText.getText(), "Vitebsk"))
                delieveryPrice = 50;
            else if (Objects.equals(cityText.getText(), "Grondo"))
                delieveryPrice = 70;
            else if (Objects.equals(cityText.getText(), "Gomel"))
                delieveryPrice = 70;
            else if (Objects.equals(cityText.getText(), "Mogilev"))
                delieveryPrice = 100;


        }
        else if (Objects.equals(deliveryChoice, "No"))
            delieveryPrice = 0;

        finalPrice = productPrice + delieveryPrice;
        priceText.setText(String.valueOf(finalPrice));
    }

    public void typeProductBox(ActionEvent event) {
    }

    public void nameProductBox(ActionEvent event) {
        int i = 0;
        var name = nameComboBox.getSelectionModel().getSelectedItem().toString();
        for(var s : shopList){
            if(Objects.equals(s.getProductName(), name)) {
                productPrice = priceProductList.get(i);
                finalPrice = delieveryPrice + priceProductList.get(i);
                priceText.setText(String.valueOf(finalPrice));
                typeText.setText(String.valueOf(typeProductList.get(i)));
            }
            else
                i++;
        }
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
}
