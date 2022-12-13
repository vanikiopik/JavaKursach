package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Entities.Order;
import com.cookos.Entities.OrderHelper;
import com.cookos.Entities.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OrderListWindowController {

    public TableColumn nameColumn;
    public TableColumn typeColumn;
    public TableColumn orderDeliveryColumn;
    public TableColumn priceColumn;
    public TableColumn reviewStatusColumn;
    public Button backButton;
    public TableView clientOrdersTable;

    private ObservableList<OrderHelper> ordersList = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject("WatchOrderList");

        var orderList = (Set<Order>)Client.istream.readObject();
        for(var s : orderList){
            ordersList.add(new OrderHelper(s));
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<OrderHelper, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<OrderHelper, String>("type"));
        orderDeliveryColumn.setCellValueFactory(new PropertyValueFactory<OrderHelper, String>("orderDelivery"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<OrderHelper, Float>("price"));
        reviewStatusColumn.setCellValueFactory(new PropertyValueFactory<OrderHelper, String>("reviewStatus"));

        clientOrdersTable.setItems(ordersList);
        
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
