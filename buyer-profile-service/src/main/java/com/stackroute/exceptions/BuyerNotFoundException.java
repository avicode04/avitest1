package com.stackroute.exceptions;

import com.stackroute.domain.Buyer;

public class BuyerNotFoundException extends Exception{
    private String message;

    public BuyerNotFoundException() {
    }

    public BuyerNotFoundException(String message) {
        this.message = message;
    }
}
