package com.stackroute.exception;

import com.stackroute.exception.ProductAlreadyExistsException;
import com.stackroute.exception.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(value=ProductNotExistsException.class)
    public ResponseEntity<String> notFoundException(ProductNotExistsException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("NotFoundException handled globally.."+ex.getMessage(), HttpStatus.CONFLICT);
        return responseEntity;
    }

    @ExceptionHandler(value=ProductAlreadyExistsException.class)
    public ResponseEntity<String> alreadyExistsException(ProductAlreadyExistsException ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("AlreadyExistsException handled globally.."+ex.getMessage(), HttpStatus.CONFLICT);
        return responseEntity;
    }

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<String> exception(Exception ex){
        ResponseEntity<String> responseEntity = new ResponseEntity("Exception handled globally.."+ex.getMessage(), HttpStatus.CONFLICT);
        return responseEntity;
    }

}
