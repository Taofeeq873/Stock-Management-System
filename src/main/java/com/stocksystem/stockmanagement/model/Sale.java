package com.stocksystem.stockmanagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    private Date dateSold;
    private  double price;
    private int quantity;

    @ManyToOne
    private User user;

    private double totalPrice;

    private int productQuantity;


    public Sale(){

    }

    public Sale(Customer customer, Product product, Date dateSold, double price, int quantity, User user, double totalPrice, int productQuantity) {
        this.customer = customer;
        this.product = product;
        this.dateSold = dateSold;
        this.price = price;
        this.quantity = quantity;
        this.user = user;
        this.totalPrice = totalPrice;
        this.productQuantity = productQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateSold() {
        return dateSold;
    }

    public void setDateSold(Date dateSold) {
        this.dateSold = dateSold;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
