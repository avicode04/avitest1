package com.stackroute.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String productName;
    private String productDescription;
    private String productImage;
    private String productBrandName;
    private String productCategory;
    private String productSubCategory;
    private double productPrice;
    private int productStock;
}
