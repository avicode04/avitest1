package com.stackroute.kafka;

import com.stackroute.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRecomDto {
    private String sellerEmail;
    private String sellerName;
    private long sellerPhone;
    private String sellerAddress;
    private String sellerGstIn;
    private double sellerRating;
    private List<Product> sellerProducts;
}
