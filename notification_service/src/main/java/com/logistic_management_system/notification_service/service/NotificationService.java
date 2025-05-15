package com.logistic_management_system.notification_service.service;

import com.logistic_management_system.notification_service.dto.*;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO);

    List<NotificationResponseDTO> getUserNotifications(String userId, boolean onlyUnread);

    NotificationResponseDTO getNotificationById(Long id);

    void markAsRead(Long id);

    void markAsUnread(Long id);

    void deleteNotification(Long id);
}
