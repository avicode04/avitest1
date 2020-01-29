package com.stackroute.exception;

public class OrderDoesNotExistException extends Exception {

    private String message;

    public OrderDoesNotExistException() {
    }

    public OrderDoesNotExistException(String message){
        this.message = message;
    }

}
