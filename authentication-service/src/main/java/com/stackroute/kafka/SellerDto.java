package com.stackroute.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDto {
    private String sellerEmail;
    private String SellerName;
    private long sellerPhone;
    private String password;
    private String role;
}
