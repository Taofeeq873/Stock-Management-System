package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Product;
import com.stocksystem.stockmanagement.model.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findProductByName(String name);

    @Query(value = "SELECT * FROM product p WHERE p.name = :name and p.product_type_id = :productType and p.quantity = :quantity", nativeQuery = true)
    List<Product> searchAvailableProducts(String name, ProductType productType, int quantity);

    Product findProductTypeByName(String name);

}
