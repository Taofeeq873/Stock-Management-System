package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findProductByName(String name);
    List<Product> findProductByQuantityGreaterThanEqual(int Quantity);

//    @Query(value = "SELECT * FROM product p WHERE p.name = :name and p.product_type_id = :productType and p.quantity = :quantity", nativeQuery = true)
//    List<Product> searchAvailableProducts(String name, ProductType productType, int quantity);

//    @Query("SELECT count(*) FROM product")
//    int countProduct();

}


