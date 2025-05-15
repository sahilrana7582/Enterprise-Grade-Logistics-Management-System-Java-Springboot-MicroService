package com.logistic_management_system.notification_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationStatusUpdateDTO {

    @NotNull(message = "Notification ID is required")
    private UUID id;

    private boolean read;
}
