package com.stackroute.exception;

public class ProductNotExistsException extends Exception{
    String message;

    public ProductNotExistsException() {
    }

    public ProductNotExistsException(String message){
        this.message=message;
    }

}
