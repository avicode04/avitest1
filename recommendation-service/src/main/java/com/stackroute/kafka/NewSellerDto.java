package com.stackroute.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewSellerDto {
    String productName;
    String sellerEmail;
    int productStock;
    double productPrice;
}
