package com.stackroute.kafka;

import com.stackroute.domain.Seller;
import lombok.Data;

import java.util.List;

@Data
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
