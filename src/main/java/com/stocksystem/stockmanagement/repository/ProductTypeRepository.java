package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductTypeRepository extends CrudRepository<ProductType, Integer> {

    ProductType findProductTypeByName(String name);
    List<ProductType> findProductTypeByNameGreaterThanEqual(String name);

}
