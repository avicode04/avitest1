package com.stackroute.exception;

public class ProductAlreadyExistsException extends Exception{

    String message;

    public ProductAlreadyExistsException() {
    }

    public ProductAlreadyExistsException(String message){
        this.message=message;
    }

}
