package com.cookos.Utilits;

import com.cookos.DBConnect.DBConnect;
import com.cookos.Entities.Shop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogTask {
    private List<Shop> shopList = new ArrayList<>();

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs =  null;


    public List<Shop> getTable() throws SQLException, ClassNotFoundException {
        con = DBConnect.getConnect();

        var query = "SELECT * FROM shop, catalog WHERE catalog.productID = Catalog_productId";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()){
            shopList.add(new Shop(
                    rs.getString("productName"),
                    rs.getString("productType"),
                    rs.getFloat("price"),
                    rs.getInt("amount")));

        }
        return shopList;
    }
}
