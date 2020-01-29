package com.stackroute.exceptions;

public class SellerNotFoundException extends Exception{
    private String message;

    public SellerNotFoundException() {
    }

    public SellerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
