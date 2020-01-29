package com.stackroute.repository;

import com.stackroute.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {
    Book findByBookTitle(String bookTitle);
}
