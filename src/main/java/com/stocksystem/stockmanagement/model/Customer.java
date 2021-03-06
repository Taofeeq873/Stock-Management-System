package com.stocksystem.stockmanagement.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String lastName;
    private String firstName;
    private String phone;
    private String address;

    @NotNull
    @Column(unique = true,length = 50)
    private String email;


    public Customer(String lastName, String firstName, String phone, String address, @NotNull String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Customer(){

    }

    public int getId() {
        return id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
