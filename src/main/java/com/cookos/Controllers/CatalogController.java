package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.DBConnect.DBConnect;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class CatalogController {
    public Button backButton;
    public Button makeOrderButton;
    @FXML
    private TableColumn<Shop, Integer> amountColumn;

    @FXML
    private TableColumn<Shop, String> nameColumn;

    @FXML
    private TableColumn<Shop, Float> priceColumn;

    @FXML
    private TableView<Shop> shopTable;

    @FXML
    private TableColumn<Shop, String> typeColumn;

    private ObservableList<Shop> shopList = FXCollections.observableArrayList();
    private List<Shop> shopList2;


    private void loadDateFromServer() throws IOException, ClassNotFoundException {
        shopList2 = (List<Shop>) Client.istream.readObject();
        shopList.addAll(shopList2);
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException, IOException {
        Client.ostream.writeObject("EnteringCatalog");
        loadDateFromServer();

        amountColumn.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("amount"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Shop, Float>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productType"));
        shopTable.setItems(shopList);
    }

    public void onBackMenuButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickOrderButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) makeOrderButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Order.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
