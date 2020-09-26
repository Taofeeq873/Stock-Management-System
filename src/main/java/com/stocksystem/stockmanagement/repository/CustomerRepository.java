package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerByAddress(String address);
    List<Customer> findCustomerByPhoneGreaterThanEqual(String phone);
    Customer findCustomerByEmail(String email);
}
