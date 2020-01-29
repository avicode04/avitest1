package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalException {

    /*exception handler of SellerAlreadyExistsException*/

    @ExceptionHandler(value = BuyerAlreadyExistException.class)
    public ResponseEntity<Object> sellerAlreadyExistsException(BuyerAlreadyExistException exception){
        return new ResponseEntity<>("Buyer Already Exists", HttpStatus.CONFLICT);
    }

    /*exception handler of SellerNotFoundException*/

    @ExceptionHandler(value = BuyerNotFoundException.class)
    public ResponseEntity<Object> sellerNotFoundException(BuyerNotFoundException exception){
        return new ResponseEntity<>("Buyer Not Found", HttpStatus.NOT_FOUND);
    }

    /*exception handler of DatabaseConnectivityFailedException*/

    @ExceptionHandler(value = DatabaseConnectivityFailedException.class)
    public ResponseEntity<Object> databaseConnectivityFailedException(BuyerNotFoundException exception){
        return new ResponseEntity<>("Database Connectivity Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
