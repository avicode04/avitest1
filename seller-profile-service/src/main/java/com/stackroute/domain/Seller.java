package com.stackroute.domain;

import com.stackroute.kafka.ProductDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "seller")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Seller {

    @Id
    private String sellerEmail;
    private String sellerName;
    private long sellerPhone;
    private String sellerAddress;
    private String sellerGstIn;
    private double sellerRating;
    private List<Product> sellerProducts = new ArrayList<Product>();
    private static final LocalDateTime timestamp= LocalDateTime.now();

    public void addInProduct(Product product){
        this.sellerProducts.add(product);
    }
}
