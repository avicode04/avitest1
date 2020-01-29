package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
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
    private List<OrderedProducts> orderedProductsList;
    private List<IncartProducts> incartProductsList;
    private List<ReturnedProducts> returnedProductsList;
    private Date buyerDob;
    private static final LocalDateTime timestamp= LocalDateTime.now();







}
