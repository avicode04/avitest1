package com.stackroute.repository;

import com.stackroute.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository marks the specific class as a Data Access Object
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    /**findByproductName method to get product by its name*/
   @Query("{'productName' : ?0}")
    public List<Product> findByProductName(String productName);
}
