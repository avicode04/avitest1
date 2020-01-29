package com.stackroute.repository;

import com.stackroute.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    public List<Order> findByBuyerEmail(String buyerEmail);

}
