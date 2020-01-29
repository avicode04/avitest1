package com.stackroute.kafka;

import com.stackroute.domain.Seller;
import com.stackroute.domain.Sellers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecomDto {
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
