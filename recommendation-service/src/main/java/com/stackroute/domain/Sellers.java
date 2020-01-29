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
public class Sellers {
    @GraphId
    private Long id;
    private String sellerEmail;
    private String sellerName;
    private long sellerPhone;
    private String sellerAddress;
    private String sellerGstIn;
    private double sellerRating;
    //private List<Product> sellerProducts;

}
