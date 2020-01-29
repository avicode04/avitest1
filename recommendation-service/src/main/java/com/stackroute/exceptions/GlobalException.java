package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @ControllerAdvice annotation provided by Spring allows you to write global
 * code that can be applied to a wide range of controllers
 */
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    /**
     * Handles BuyerNotFound exception
     */
    @ExceptionHandler(value = BuyerNotFoundException.class)
    public ResponseEntity<Object> buyerNotFoundException(BuyerNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BuyerAlreadyExistsException
     */
    @ExceptionHandler(value = BuyerAlreadyExistException.class)
    public ResponseEntity<Object> buyerAlreadyExistsException(BuyerAlreadyExistException exception) {
        return new ResponseEntity<>("Product already exists", HttpStatus.CONFLICT);
    }

    /**
     * Handles ProductNotFound exception
     */
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BuyerAlreadyExistsException
     */
    @ExceptionHandler(value = ProductAlreadyExistsException.class)
    public ResponseEntity<Object> productAlreadyExistsException(ProductAlreadyExistsException exception) {
        return new ResponseEntity<>("Product already exists", HttpStatus.CONFLICT);
    }

    /**
     * Handles BuyerNotFound exception
     */
    @ExceptionHandler(value = SellerNotFoundException.class)
    public ResponseEntity<Object> sellerNotFoundException(SellerNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles BuyerAlreadyExistsException
     */
    @ExceptionHandler(value = SellerAlreadyExistsException.class)
    public ResponseEntity<Object> alreadyExistsException(SellerAlreadyExistsException exception) {
        return new ResponseEntity<>("Product already exists", HttpStatus.CONFLICT);
    }

    /**
     * Handles Internal_Server_Error i.e if database connection fails
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>("Database connectivity is lost", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

