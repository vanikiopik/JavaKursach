package com.cookos.Controllers;

import com.cookos.DBConnect.DBConnect;
import com.cookos.Entities.Shop;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.engine.jdbc.ReaderInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    String query = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs=  null;
    Shop shop = null;

    @FXML
    private void refreshTable() throws SQLException {
        shopList.clear();
        var query = "SELECT * FROM shop, catalog WHERE catalog.productID = Catalog_productId";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()){
            shopList.add(new Shop(
                    rs.getString("productName"),
                    rs.getString("productType"),
                    rs.getFloat("price"),
                    rs.getInt("amount")));
            shopTable.setItems(shopList);
        }
    }

    private void loadDate() throws SQLException, ClassNotFoundException {
        con = DBConnect.getConnect();

        refreshTable();
        amountColumn.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("amount"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Shop, Float>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productType"));
    }
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        loadDate();
        /*initData();
        amountColumn.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("amount"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Shop, Float>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Shop, String>("productType"));

        shopTable.setItems(shopList);*/
    }



    private void initData(){
        shopList.add(new Shop("ASd", "asd", 13, 2));
        shopList.add(new Shop("Giga", "Omage", 10, 5));
    }
}
