package com.cookos.Utilits;

import com.cookos.DBConnect.DBConnect;
import com.cookos.Entities.Order;
import com.cookos.Entities.OrderHelper;
import com.cookos.Entities.Shop;
import com.cookos.Entities.User;
import com.cookos.Patterns.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderTask {
    private List<Shop> shopList = new ArrayList<>();
    private List<Order> ordersList = new ArrayList<>();

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    public List<Shop> getAvailableProduct() throws SQLException, ClassNotFoundException {
        shopList.clear();
        con = DBConnect.getConnect();

        var query = "SELECT * FROM shop, catalog WHERE catalog.productID = Catalog_productId AND shop.amount > 0";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            shopList.add(new Shop(
                    rs.getString("productName"),
                    rs.getString("productType"),
                    rs.getFloat("price"),
                    rs.getInt("amount")));
        }
        return shopList;
    }

    /*public List<Order> getUserOrders(int userID) throws SQLException, ClassNotFoundException {
        *//*con = DBConnect.getConnect();

        var query = "SELECT * FROM orders, shop, catalog  WHERE User_userID = " + userID + " and orders.Shop_Catalog_productID = shop.Catalog_productID = catalog.productID";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

*//**//*        while (rs.next()) {
            ordersList.add(new OrderHelper(
                    rs.getString("orderStatus"),
                    rs.getString("orderDelivery"),
                    rs.getFloat("finalPrice"),
                    rs.getString("productType"),
                    rs.getString("productName")));*//**//*

        }
        return ordersList;*//*
    }*/
}
