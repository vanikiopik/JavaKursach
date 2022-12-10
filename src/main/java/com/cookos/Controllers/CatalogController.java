package com.cookos.Controllers;

import com.cookos.Entities.Catalog;
import com.cookos.Entities.Shop;
import com.cookos.Entities.User;
import com.cookos.bdConnector.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.annotations.DialectOverride;

import java.sql.*;

public class CatalogController {

    @FXML
    private TableView<Catalog> commonTable;

    @FXML
    private TableColumn<Shop, Integer> amountTable;

    @FXML
    private TableColumn<Shop, Float> priceColumn;

    @FXML
    private TableColumn<Catalog, String> nameColumn;

    @FXML
    private TableColumn<Catalog, String> typeColumn;

    private ObservableList<Catalog> shopList = FXCollections.observableArrayList();



    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        initData();

        amountTable.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Shop, Float>("price"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Catalog, String >("productName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Catalog, String>("productType"));

        commonTable.setItems(shopList);
    }

    private void initData(){

        shopList.add(new Shop("Iphone 14", "Smartphone", 1400, 2));
        shopList.add(new Shop("Iphone XR", "Smartphone", 1200, 5));
        shopList.add(new Shop("Cort G110", "Guitar", 40, 3));


    }
}
