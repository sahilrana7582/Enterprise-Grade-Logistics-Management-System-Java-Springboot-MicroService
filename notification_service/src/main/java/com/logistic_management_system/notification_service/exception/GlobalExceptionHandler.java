package com.logistic_management_system.notification_service.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {


    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(NotificationNotFoundException ex,
                                                                       HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .urlPath(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception ex,
                                                       HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .urlPath(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }
}
