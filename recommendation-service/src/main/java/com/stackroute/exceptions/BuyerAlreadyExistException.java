package com.stackroute.exceptions;

public class BuyerAlreadyExistException extends Exception {
    private String message;

    public BuyerAlreadyExistException() {
    }

    public BuyerAlreadyExistException(String message) {
        this.message = message;
    }
}
