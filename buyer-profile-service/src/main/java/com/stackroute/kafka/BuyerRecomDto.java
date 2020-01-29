package com.stackroute.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerRecomDto {
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
