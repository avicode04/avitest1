package com.stackroute.kafka;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRecomDto {
    private String bookISBN;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String sellerEmail;
    private String bookCategory;
    private String bookSubcategory;
    private double bookPrice;
    private int bookStock;
}
