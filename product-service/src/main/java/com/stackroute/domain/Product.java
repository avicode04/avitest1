package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Document annotated class will have the ability to represent objects in the database
 */
@Document(collection = "products")

/**With @Data, Lombok will generate getter and setter methods, toString methods, Equal & Hashcode methods*/
@Data

/**@NoArgsConstructor will generate constructor with no arguments*/
@NoArgsConstructor

/**@AllArgsConstructor will generate constructor with all properties in the class*/
@AllArgsConstructor
@Builder
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
