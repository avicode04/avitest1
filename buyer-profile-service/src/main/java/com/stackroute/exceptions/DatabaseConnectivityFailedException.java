package com.stackroute.exceptions;

public class DatabaseConnectivityFailedException extends Throwable {
    private String message;

    public DatabaseConnectivityFailedException() {
    }

    public DatabaseConnectivityFailedException(String message) {
        this.message = message;
    }
}
