package com.stackroute.exceptions;


public class BuyerNotFoundException extends Exception {
    private String message;

    public BuyerNotFoundException() {
    }

    public BuyerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
