package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Buyer")
public class Buyer {
    @Id
    private String buyerEmail;
    private String buyerName;
    private String buyerImage;
    private String buyerGender;
    private String password;
    private long buyerPhone;
    private String buyerHomeAddress;
    private String buyerOfficeAddress;
    private double buyerRating;
}