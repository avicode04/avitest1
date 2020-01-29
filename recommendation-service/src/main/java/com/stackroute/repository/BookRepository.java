package com.stackroute.repository;

import com.stackroute.domain.Book;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends Neo4jRepository<Book,Long> {
    @Query("Match (p1:Book),(s:SubCategory) where p1.bookSubCategory=s.subCategoryNameOrGenre AND id(p1)={bookId} Create (p1)-[r:BelongsTo]->(s)")
    void postBookCategory(@Param("bookId") Long bookId);

    @Query("MATCH (s:Sellers),(p:Book) where s.sellerEmail={sellerEmail} AND p.bookTitle={bookName} CREATE (s)-[r:ADD2INVENTORY{quantity:{bookQty},price:{bookPrice}}]->(p)")
    void AddSellers(@Param("sellerEmail") String sellerEmail, @Param("bookName") String bookName, @Param("bookQty") int bookQty, @Param("bookPrice") Double bookPrice);

    @Query("MATCH (s:Sellers),(p:Book) where s.sellerEmail={sellerEmail} AND p.bookTitle={bookName} CREATE (s)-[r:ADD2INVENTORY{quantity:{bookQty},price:{bookPrice}}]->(p)")
    void AddNewSellers(@Param("sellerEmail") String sellerEmail, @Param("bookName") String bookName, @Param("bookQty") int bookQty, @Param("bookPrice") Double bookPrice);


}
