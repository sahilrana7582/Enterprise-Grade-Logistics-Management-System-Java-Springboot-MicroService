package com.logistic_management_system.notification_service.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetail {
    private HttpStatus status;
    private String message;
    private String urlPath;
    private LocalDateTime timestamp;
}
