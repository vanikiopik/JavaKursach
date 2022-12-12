package com.cookos.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Entity
@Setter
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "Catalog_productID")
public class Shop extends Catalog implements Serializable {

    @Column(name = "price")
    private float price;

    @Column(name = "amount")
    private int amount;

    public Shop(String productName, String productType, float price, int amount) {
        super(productName, productType);
        this.price = price;
        this.amount = amount;
    }

    public Shop() {
        super();
    }

}
