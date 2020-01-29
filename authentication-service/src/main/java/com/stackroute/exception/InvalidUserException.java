package com.stackroute.exception;

public class InvalidUserException extends Exception {

    private  String message;

    public InvalidUserException() {
    }

    public InvalidUserException(String message) {
        super(message);
        this.message = message;
    }
}
