package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    /*exception handler of SellerAlreadyExistsException*/

    @ExceptionHandler(value = SellerAlreadyExistsException.class)
    public ResponseEntity<Object> sellerAlreadyExistsException(SellerAlreadyExistsException exception){
        return new ResponseEntity<>("Seller Already Exists", HttpStatus.CONFLICT);
    }

    /*exception handler of SellerNotFoundException*/

    @ExceptionHandler(value = SellerNotFoundException.class)
    public ResponseEntity<Object> sellerNotFoundException(SellerNotFoundException exception){
        return new ResponseEntity<>("Seller Not Found", HttpStatus.NOT_FOUND);
    }

    /*exception handler of DatabaseConnectivityFailedException*/

    @ExceptionHandler(value = DatabaseConnectivityFailedException.class)
    public ResponseEntity<Object> databaseConnectivityFailedException(SellerNotFoundException exception){
        return new ResponseEntity<>("Database Connectivity Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
