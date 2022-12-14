package com.cookos.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productType")
    private String productType;

    public Catalog(String productName, String productType) {
        this.productName = productName;
        this.productType = productType;
    }

    public Catalog() {

    }
}
