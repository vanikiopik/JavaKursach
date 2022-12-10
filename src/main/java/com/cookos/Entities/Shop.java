package com.cookos.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Builder
@AllArgsConstructor
public class Shop extends Catalog {
    @Id
    private int Catalog_productID;

    private float price;

    private int amount;

    public Shop(String productName, String productType, float price, int amount) {
        super(productName, productType);
        this.price = price;
        this.amount = amount;
    }

    public Shop() {

    }
}
