package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.ProductType;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

    ProductType findProductTypeByName(String name);
    List<ProductType> findProductTypeById(int id);

//     ProductType deleteProductTypeById(int id);

//    @Query(value = "SELECT COUNT(*) FROM product_type", nativeQuery = true)
//    int countAllProductType();

//    @Query(value = "SELECT COUNT(*) FROM product_type", nativeQuery = true)
//    int allProductTypes();
}
