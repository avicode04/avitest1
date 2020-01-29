package com.stackroute.repository;

import com.stackroute.domain.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends MongoRepository<Buyer, String> {

    /**
     * findByBuyerName method to get buyer by its name
     */
    public List<Buyer> findByBuyerName(String BuyerName);

}

