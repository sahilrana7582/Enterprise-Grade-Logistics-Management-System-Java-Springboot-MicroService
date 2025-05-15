package com.logistic_management_system.notification_service.exception;

public class NotificationNotFoundException extends RuntimeException{

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
