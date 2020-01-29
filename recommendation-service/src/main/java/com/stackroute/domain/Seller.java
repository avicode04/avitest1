package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    private String sellerId;
    private String sellerName;
    private double sellerRatings;
    private int productReturned;
    private double productPrice;
    private int productStock;
    private int productSold;
    private double sellerIndex;
}
