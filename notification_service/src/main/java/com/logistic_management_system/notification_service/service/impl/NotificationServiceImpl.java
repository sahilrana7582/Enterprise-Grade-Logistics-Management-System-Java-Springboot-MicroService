package com.logistic_management_system.notification_service.service.impl;

import com.logistic_management_system.notification_service.dto.*;
import com.logistic_management_system.notification_service.exception.NotificationNotFoundException;
import com.logistic_management_system.notification_service.model.Notification;
import com.logistic_management_system.notification_service.repository.NotificationRepository;
import com.logistic_management_system.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO) {
        Notification notification = Notification.builder()
                .userId(requestDTO.getUserId())
                .title(requestDTO.getTitle())
                .message(requestDTO.getMessage())
                .type(requestDTO.getType())
                .read(false)
                .build();

        notification = notificationRepository.save(notification);
        return mapToDTO(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getUserNotifications(String userId, boolean onlyUnread) {
        List<Notification> notifications = notificationRepository.findAll();

        return notifications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponseDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + id));

        return mapToDTO(notification);
    }

    @Override
    @Transactional
    public void markAsRead(Long id) {
        Notification notification = getNotificationEntity(id);
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void markAsUnread(Long id) {
        Notification notification = getNotificationEntity(id);
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void deleteNotification(Long id) {
        Notification notification = getNotificationEntity(id);
        notificationRepository.delete(notification);
    }

    // ===================== PRIVATE HELPERS ========================

    private Notification getNotificationEntity(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + id));
    }

    private NotificationResponseDTO mapToDTO(Notification notification) {
        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .read(notification.isRead())
                .build();
    }
}
