package com.stackroute.exceptions;

public class OrderAlreadyExistsException extends Exception {
    private String message;

    public OrderAlreadyExistsException() {
    }

    public OrderAlreadyExistsException(String message) {
        this.message = message;
    }
}
