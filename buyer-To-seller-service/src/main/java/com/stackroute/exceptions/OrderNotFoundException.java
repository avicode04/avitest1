package com.stackroute.exceptions;

public class OrderNotFoundException extends Exception {
    private String message;

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
