package com.stackroute.exceptions;

public class SellerNotFoundException extends Exception{
    private String message;

    public SellerNotFoundException() {
    }

    public SellerNotFoundException(String message) {
        this.message = message;
    }
}
