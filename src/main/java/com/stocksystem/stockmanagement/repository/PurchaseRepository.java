package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findPurchaseByQuantityGreaterThanEqual(int quantity);
    Purchase findPurchaseBySupplier_LastName(String lastName);
    Purchase findPurchaseByName(String name);




}
