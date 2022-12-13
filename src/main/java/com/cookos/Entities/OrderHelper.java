package com.cookos.Entities;

import lombok.Data;

@Data

public class OrderHelper {

    private String name;
    private String type;

    private String orderDelivery;
    private float price;
    private String reviewStatus;

    public OrderHelper(Order order) {
        this.name = order.getShop_Catalog_productID().getProductName();
        this.type = order.getShop_Catalog_productID().getProductType();
        this.orderDelivery = order.getOrderDelivery();
        this.price = order.getFinalPrice();
        this.reviewStatus = order.getOrderStatus();
    }
}
