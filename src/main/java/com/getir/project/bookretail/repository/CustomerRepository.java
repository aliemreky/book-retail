package com.getir.project.bookretail.repository;

import com.getir.project.bookretail.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
