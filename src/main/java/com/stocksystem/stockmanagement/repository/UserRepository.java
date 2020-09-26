package com.stocksystem.stockmanagement.repository;

import com.stocksystem.stockmanagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean   existsByUsername(String username);
    List<User> findUserByFirstNameGreaterThanEqual(String firstName);
    User findUserByLastName(String lastName);
}
