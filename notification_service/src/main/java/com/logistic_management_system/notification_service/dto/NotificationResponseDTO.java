package com.logistic_management_system.notification_service.dto;

import com.logistic_management_system.notification_service.enums.NotificationType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {

    private Long id;
    private String userId;
    private String title;
    private String message;
    private NotificationType type;
    private boolean read;


}
