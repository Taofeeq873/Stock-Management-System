package com.stocksystem.stockmanagement.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @NotNull
//    @Column(unique = true,length = 50)
    private String name;

//    @ManyToOne
    private String productType;

    private int quantity;

    private String description;

//    @ManyToOne
    private String supplier;

    private double price;

    private Date datePurchased;

//    @ManyToOne
//    private Purchase purchase;

    public Product(){

    }

    public Product(String name, String productType, int quantity,String description,String supplier,double price, Date datePurchased) {
        this.name = name;
        this.productType = productType;
        this.quantity = quantity;
        this.description = description;
        this.supplier = supplier;
        this.price = price;
        this.datePurchased = datePurchased;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

//    public Purchase getPurchase() {
//        return purchase;
//    }
//
//    public void setPurchase(Purchase purchase) {
//        this.purchase = purchase;
//    }
}

