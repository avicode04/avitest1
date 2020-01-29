package com.stackroute.exceptions;

public class BookAlreadyExistsException extends Exception{
    private String message;

    public BookAlreadyExistsException() {
    }

    public BookAlreadyExistsException(String message) {
        this.message = message;
    }
}
