package com.stackroute.kafka;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {
    private String productName;
    private String productDescription;
    private String productImage;
    private String productBrandName;
    private String sellerEmail;
    private String productCategory;
    private String productSubCategory;
    private double productPrice;
    private int productStock;
}
