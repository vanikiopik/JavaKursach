package com.cookos.Controllers;

import com.cookos.Entities.Shop;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CatalogController {
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
    @FXML
    private void initialize(){
        initData();
        amountColumn.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("amount"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Shop, Float>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productType"));

        shopTable.setItems(shopList);
    }

    private void initData(){
        shopList.add(new Shop("ASd", "asd", 13, 2));
    }
}
