package com.stackroute.repository;

import com.stackroute.domain.Buyer;
import com.stackroute.domain.Products;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyerRepository extends Neo4jRepository<Buyer, Long> {

    @Query("MATCH (b:Buyer)-[r:BUYS]->(p:Products) where id(b)={buyerId} RETURN p")
    List<Products> getAllBuyedProducts(@Param("buyerId") Long buyerId);

    @Query("MATCH (b:Buyer)-[r:ADD2CART]->(p:Products) where id(b)={buyerId} RETURN p")
    List<Products> getAllCartProducts(@Param("buyerId") Long buyerId);

    @Query("MATCH (b:Buyer),(p:Products) where id(b)={buyerId} AND id(p)={productId} CREATE (b)-[r:BUYS]->(p)")
    void buysProduct(@Param("buyerId") Long buyerId,@Param("productId") Long productId);

    @Query("MATCH (b:Buyer),(p:Products) where id(b)={buyerId} AND id(p)={productId} CREATE (b)-[r:ADD2CART]->(p)")
    void AddToCartProduct(@Param("buyerId") Long buyerId,@Param("productId") Long productId);

    @Query("MATCH (b)-[r:ADD2CART]->(p) where id(b)={buyerId} AND id(p)={productId} DELETE r")
    void DeleteFromCartProduct(@Param("buyerId") Long buyerId,@Param("productId") Long productId);

    @Query("Match (b:Buyer) where b.buyerEmail={emailId} return b")
    Buyer findByEmailId(@Param("emailId") String emailId);


}
