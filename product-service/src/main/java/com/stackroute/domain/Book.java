package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Document annotated class will have the ability to represent objects in the database
 */
@Document(collection = "book")

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private String bookId;
    private String bookISBN;
    private String bookTitle;
    private String bookAuthor;
    private String bookImage;
//    private String bookGenre;
    private String bookDesc;
    private String bookPublisher;
    private String bookCategory;
    private String bookSubcategory;
    private List<Seller> sellers;
}
