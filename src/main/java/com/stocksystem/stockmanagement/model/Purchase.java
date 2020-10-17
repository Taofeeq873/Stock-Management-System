package com.stocksystem.stockmanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Supplier supplier;

    private String name;
    private double price;
    private int quantity;
    private Date datePurchased;

    @ManyToOne
    private ProductType productType;

    public Purchase(){

    }

    public Purchase(Supplier supplier, String name, double price, int quantity, Date datePurchased, ProductType productType) {
        this.supplier = supplier;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.datePurchased = datePurchased;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
