package com.stackroute.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncartProducts {

    private String productId;
    private String orderId;
    private String customerId;
    private String sellerId;
    private BigDecimal productPrice;
    private long productQuantity;


}
