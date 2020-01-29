package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private String productBrandName;
    private List<Seller> sellers;
    private String productCategory;
    private String productSubCategory;
}

