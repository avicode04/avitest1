package com.stackroute.repository;

import com.stackroute.domain.Product;
import com.stackroute.domain.Products;
import com.stackroute.domain.Sellers;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SellerRepository extends Neo4jRepository<Sellers, Long> {

    @Query("MATCH (s:Sellers)-[r:SOLD]->(p:Product) where id(s)={sellerId} RETURN p")
    List<Products> getAllSoldProducts(@Param("sellerId") Long sellerId);

    @Query("MATCH (s:Sellers)-[r:ADD2INVENTORY]->(p:Products) where id(s)={sellerId} RETURN p")
    List<Products> getAllInventoryProducts(@Param("sellerId") Long sellerId);

    @Query("MATCH (s:Sellers),(p:Products) where id(s)={sellerId} AND id(p)={productId} CREATE (s)-[r:SOLD]->(p)")
    void soldProduct(@Param("sellerId") Long sellerId,@Param("productId") Long productId);

    @Query("MATCH (s:Sellers),(p:Products) where id(s)={sellerId} AND id(p)={productId} CREATE (s)-[r:ADD2INVENTORY]->(p)")
    void AddToInventoryProduct(@Param("sellerId") Long sellerId,@Param("productId") Long productId);

    @Query("MATCH (s:Sellers)-[r:ADD2INVENTORY]->(p:Products) where id(s)={sellerId} AND id(p)={productId} DELETE r")
    void DeleteFromInventoryProduct(@Param("sellerId") Long sellerId,@Param("productId") Long productId);

    @Query("Match (s:Sellers) where s.sellerEmail={emailId} return s")
    Sellers findByEmailId(@Param("emailId") String emailId);

    @Query("MATCH (s:Sellers),(p:Products) where id(s)={sellerId} AND p.productName={productName} CREATE (s)-[r:ADD2INVENTORY{quantity:{productQty},price:{productPrice}}]->(p)")
    void AddProduct(@Param("sellerId") Long sellerId, @Param("productName") String productName, @Param("productQty") Long productQty, @Param("productPrice") Double productPrice);


}