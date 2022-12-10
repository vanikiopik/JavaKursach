package com.cookos.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity

public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    private String productName;

    private String productType;

    public Catalog() {

    }

    public Catalog(String productName, String productType) {
        this.productName = productName;
        this.productType = productType;
    }

}
