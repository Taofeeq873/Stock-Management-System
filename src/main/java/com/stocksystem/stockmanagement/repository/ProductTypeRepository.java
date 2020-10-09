package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.ProductType;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductTypeRepository extends CrudRepository<ProductType, Integer> {

    ProductType findProductTypeByName(String name);
    List<ProductType> findProductTypeByNameGreaterThanEqual(String name);

    @Query(value = "SELECT COUNT(*) FROM product_type", nativeQuery = true)
    int countAllProductType();
}
