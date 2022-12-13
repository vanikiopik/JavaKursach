package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AdminReviewOrderController {


    public ComboBox chooseIdComboBox;
    public TextField productNameField;
    public TextField productTypeField;
    public TextField productPriceField;
    public TextField productDeliveryField;
    public ComboBox reviewStatusComboBox;
    public Button confirmButton;
    public Button backButton;

    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Integer> orderIDs = FXCollections.observableArrayList();

    private ObservableList<String> statusStrings = FXCollections.observableArrayList("Review", "Accept", "Reject");
    List<Order> ordersList = null;

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject("SendOrdersToReview");

        ordersList = (List<Order>) Client.istream.readObject();

        for(var s : ordersList){
            orderList.add(s);
        }
        for (var s : ordersList){
            orderIDs.add(s.getOrderID());
        }
        chooseIdComboBox.setItems(orderIDs);
        reviewStatusComboBox.setItems(statusStrings);
    }

    public void onSelectedIdValue(ActionEvent event) {
        var choiceMessageId = (Integer)chooseIdComboBox.getSelectionModel().getSelectedItem();
        for (var s : ordersList)
            if(choiceMessageId == s.getOrderID())
            {
                productNameField.setText(s.getShop_Catalog_productID().getProductName());
                productTypeField.setText(s.getShop_Catalog_productID().getProductType());
                productPriceField.setText(String.valueOf(s.getFinalPrice()));
                productDeliveryField.setText(s.getOrderDelivery());
                reviewStatusComboBox.setValue(s.getOrderStatus());
            }
    }

    public void onSelectedStatusValue(ActionEvent event) {
    }

    public void onConfirmButtonClick(ActionEvent event) {

    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
