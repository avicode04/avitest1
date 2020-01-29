package com.stackroute.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDto {

        private String buyerEmail;
        private String buyerName;
        private long buyerPhone;
        private String password;
        private String role="buyer";
}
