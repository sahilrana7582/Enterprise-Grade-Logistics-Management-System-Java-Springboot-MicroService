package com.logistic_management_system.user_service.exception;

import org.springframework.http.HttpStatus;



public class ResourceNotFoundException extends RuntimeException {
    private final HttpStatus status;
    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
