package com.cookos.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
@Entity
public class Catalog implements Serializable {
    @Id
    private int productID;

    private String productName;

    private String productType;

    public Catalog(String productName, String productType) {
        this.productName = productName;
        this.productType = productType;
    }

    public Catalog() {

    }
}
