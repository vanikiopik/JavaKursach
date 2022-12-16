package com.cookos.Entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "User_userID", referencedColumnName = "userID")
    private User User_userID;

    @ManyToOne
    @JoinColumn(name = "Shop_Catalog_productID", referencedColumnName = "Catalog_productID")
    private Shop Shop_Catalog_productID;

    private String orderStatus;

    private String orderDelivery;

    private float finalPrice;

    public Order(String status, String orderDelivery,
                 float finalPrice) {
        this.orderStatus = status;
        this.orderDelivery = orderDelivery;
        this.finalPrice = finalPrice;
    }

    public Order() {
    }

    public Order(String review, Float finalPrice, String deliveryStatus) {
        this.orderStatus = review;
        this.finalPrice = finalPrice;
        this.orderDelivery = deliveryStatus;
    }
}
