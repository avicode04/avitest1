package com.stackroute.exceptions;

/**
 * Custom Exception to throw if track already exists
 */
public class ProductAlreadyExistsException extends Exception{
    private String message;

    public ProductAlreadyExistsException() {
    }

    public ProductAlreadyExistsException(String message) {
        this.message = message;
    }
}
