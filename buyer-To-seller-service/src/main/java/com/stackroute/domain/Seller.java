package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Document annotated class will have the ability to represent objects in the database
 */
@Document(collection = "sellers")

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor

@Builder

public class Seller {
    /**
     * Id annotation makes id variable as Primary key
     */
    @Id
    private String sellerId;
    private String sellerName;
    private double sellerRatings;
    private double productPrice;
    private int productStock;
    private int productSold;
    private int productReturned;
    private double sellerIndex;
    private double sqSellerIndex;
    private int numOrdersReceived;
}
