package com.stackroute.exceptions;

/**
 * Custom Exception to throw if track is not found
 */
public class ProductNotFoundException extends Exception{
    private String message;

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
