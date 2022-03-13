package com.getir.project.bookretail.repository;

import com.getir.project.bookretail.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findAllByIdIn(Collection<Long> id);

    List<Order> findAllByCreatedDateBetween(LocalDateTime createdDateStart, LocalDateTime createdDateEnd);

    @Query("{'customer.email' : ?0}")
    List<Order> findAllByCustomer(String email);
}
