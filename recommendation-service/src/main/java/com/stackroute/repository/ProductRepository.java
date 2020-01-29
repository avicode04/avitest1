package com.stackroute.repository;

import com.stackroute.domain.Products;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends Neo4jRepository<Products,Long> {

    @Query("Match (p1:Products),(p2:Products) where NOT id(p1)=id(p2) AND id(p1)={productId} AND p2.productPrice>(p1.productPrice-0.1*(p1.productPrice)) AND p2.productPrice<(p1.productPrice+0.1*(p1.productPrice)) AND p1.productSubCategory=p2.productSubCategory create (p1)-[r:SamePriceRange]->(p2)")
    void postProductSamePriceRange(@Param("productId") Long productId);

    @Query("Match (p1:Products),(p2:Products) where NOT id(p1)=id(p2) AND id(p1)={productId} AND p1.product=p2.productBrandName AND p1.productCategory=p2.productCategory create (p1)-[r:SameBrand]->(p2)")
    void postProductSameBrand(@Param("productId") Long productId);

    @Query("Match (p1:Products),(p2:Products) where NOT id(p1)=id(p2) AND id(p1)={productId} AND p1.product=p2.productBrandName AND p1.productCategory=p2.productCategory create (p1)-[r:RelatedProduct]->(p2)")
    void postProductSimilarRelation(@Param("productId") Long productId);

    @Query("Match (p1:Products)-[r:SamePriceRange]->(p2:Products) where NOT id(p1)=id(p2) AND id(p1)={productId} return p2")
    List<Products> getProductSamePriceRange(@Param("productId") Long productId);

    @Query("Match (p1:Products)-[r:SameBrand]->(p2:Products) where NOT id(p1)=id(p2) AND id(p1)={productId} return p2")
    List<Products> getProductSameBrand(@Param("productId") Long productId);

    @Query("Match (p1:Products)-[r:RelatedProduct]->(p2:Products) where NOT id(p1)=id(p2) AND id(p1)={productId} return p2")
    List<Products> getRelatedProduct(@Param("productId") Long productId);

    @Query("Match (p1:Products),(s:SubCategory) where p1.productSubCategory=s.subCategoryNameOrGenre AND id(p1)={productId} Create (p1)-[r:BelongsTo]->(s)")
    void postProductCategory(@Param("productId") Long productId);

    @Query("MATCH (s:Sellers),(p:Products) where s.sellerEmail={sellerEmail} AND p.productName={productName} CREATE (s)-[r:ADD2INVENTORY{quantity:{productQty},price:{productPrice}}]->(p)")
    void AddSellers(@Param("sellerEmail") String sellerEmail, @Param("productName") String productName, @Param("productQty") int productQty, @Param("productPrice") Double productPrice);

    @Query("MATCH (s:Sellers),(p:Products) where s.sellerEmail={sellerEmail} AND p.productName={productName} CREATE (s)-[r:ADD2INVENTORY{quantity:{productQty},price:{productPrice}}]->(p)")
    void AddNewSellers(@Param("sellerEmail") String sellerEmail, @Param("productName") String productName, @Param("productQty") int productQty, @Param("productPrice") Double productPrice);



}