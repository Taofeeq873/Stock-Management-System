package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

    Supplier findSupplierByCompanyName(String companyName);
    List<Supplier> findSupplierByPhoneGreaterThanEqual(String phone);
}
