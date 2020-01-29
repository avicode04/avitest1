package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProducts {

    private String productId;
    private String orderId;
    private String customerId;
    private String sellerId;
    private BigDecimal productPrice;
    private long productQuantity;


}
