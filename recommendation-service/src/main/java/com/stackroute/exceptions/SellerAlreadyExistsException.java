package com.stackroute.exceptions;

public class SellerAlreadyExistsException extends Exception {
    private String message;

    public SellerAlreadyExistsException() {
    }

    public SellerAlreadyExistsException(String message) {
        this.message = message;
    }
}
