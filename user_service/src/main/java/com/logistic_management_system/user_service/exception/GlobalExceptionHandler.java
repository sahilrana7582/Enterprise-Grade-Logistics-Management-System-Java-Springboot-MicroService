package com.logistic_management_system.user_service.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                       HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND);
        errorDetail.setTimestamp(LocalDateTime.now());
        errorDetail.setUrlPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception ex,
                                                                       HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Internal Server Error");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorDetail.setTimestamp(LocalDateTime.now());
        errorDetail.setUrlPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }
}
