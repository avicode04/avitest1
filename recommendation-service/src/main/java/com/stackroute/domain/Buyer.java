package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class Buyer {

    @GraphId
    private Long id;
    private String buyerEmail;
    private String buyerName;
    private String buyerImage;
    private String buyerGender;
    private long buyerPhone;
    private String buyerHomeAddress;
    private String buyerOfficeAddress;
    private double sellerRating;
    private Date buyerDob;
}
