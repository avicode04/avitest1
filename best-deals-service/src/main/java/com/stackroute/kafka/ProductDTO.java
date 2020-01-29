package com.stackroute.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private String description;
    private String brandName;
    private double price;
    private String category;
    private String subCategory;

}
