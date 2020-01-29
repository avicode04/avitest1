package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Order")
public class Order {

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
