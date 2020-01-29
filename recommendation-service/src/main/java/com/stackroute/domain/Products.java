package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Products {

    @GraphId
    private Long id;

    private String productName;
    private String productDescription;
    private String productImage;
    private String productBrandName;
    //private List<Seller> sellers;
    private String productCategory;
    private String productSubCategory;
}
