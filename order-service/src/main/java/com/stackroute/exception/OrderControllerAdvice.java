package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(value = OrderDoesNotExistException.class)
    public ResponseEntity<String> notFoundException(OrderDoesNotExistException e) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("ControllerAdvice: Order Does Not Exist Exception.\n" +
                e.getMessage(), HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = OrderAlreadyExistsException.class)
    public ResponseEntity<String> alreadyExistsException(OrderAlreadyExistsException e) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("ControllerAdvice: Order Already Exists Exception.\n" +
                e.getMessage(), HttpStatus.CONFLICT);
        return responseEntity;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exception(Exception e){
        ResponseEntity<String> responseEntity = new ResponseEntity("ControllerAdvice: Internal Server Error.\n" +
                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
