package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


/**
 * Document annotated class will have the ability to represent objects in the database
 */
@Document(collection = "orders")

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Builder
public class Order {

    /*@Transient
    public static final String SEQUENCE_NAME = "orders_sequence";*/

    @Id
    private String orderId;
    private String buyerEmail;
    private Double rating;
    private List<Products> products;
    private long buyerPhone;
    private String buyerHomeAddress;
    private String buyerOfficeAddress;
    private Date timestamp;
    private Date finishTimestamp;
    private String status;

}
