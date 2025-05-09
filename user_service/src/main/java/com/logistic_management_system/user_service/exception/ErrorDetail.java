package com.logistic_management_system.user_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail {

    private String title;
    private HttpStatus status;
    private String detail;
    private LocalDateTime timestamp;
    private String urlPath;
}
