package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Sale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Integer> {

    List<Sale> findSaleByQuantityGreaterThanEqual(int quantity);
    Sale findPurchaseByCustomer_LastName(String lastName);
}
