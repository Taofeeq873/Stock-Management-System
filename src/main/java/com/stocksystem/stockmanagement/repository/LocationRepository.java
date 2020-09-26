package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    Location findLocationByCode(String code);
}
