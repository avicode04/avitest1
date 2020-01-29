package com.stackroute.exceptions;

public class BookNotFoundException extends Exception{
    private String message;

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
