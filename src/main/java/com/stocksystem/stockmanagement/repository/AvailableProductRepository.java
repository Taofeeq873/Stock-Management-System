package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.AvailableProduct;
import com.stocksystem.stockmanagement.model.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvailableProductRepository extends CrudRepository<AvailableProduct, Integer> {

    List<AvailableProduct> findAvailableProductByQuantityGreaterThanEqual(int quantity);
    AvailableProduct findAvailableProductById(int id);
    AvailableProduct findAvailableProductByName(String name);
}
