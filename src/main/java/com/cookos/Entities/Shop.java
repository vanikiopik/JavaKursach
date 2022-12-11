package com.cookos.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
@Entity
public class Shop extends Catalog implements Serializable {

    private float price;

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
