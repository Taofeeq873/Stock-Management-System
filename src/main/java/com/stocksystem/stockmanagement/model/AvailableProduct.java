package com.stocksystem.stockmanagement.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AvailableProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

//    @ManyToOne
    private String supplier;

    private int quantity;

    private Date datePurchased;

    public AvailableProduct(){

    }

    public AvailableProduct(String name, String supplier, int quantity, Date datePurchased) {
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
}
