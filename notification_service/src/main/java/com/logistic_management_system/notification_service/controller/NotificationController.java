package com.logistic_management_system.notification_service.controller;

import com.logistic_management_system.notification_service.dto.NotificationRequestDTO;
import com.logistic_management_system.notification_service.dto.NotificationResponseDTO;
import com.logistic_management_system.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(
            @Valid @RequestBody NotificationRequestDTO requestDTO) {
        NotificationResponseDTO created = notificationService.createNotification(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(
            @PathVariable("userId") String userId,
            @RequestParam(name = "unreadOnly", required = false, defaultValue = "false") boolean unreadOnly) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId, unreadOnly));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> getNotificationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable("id") Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/unread")
    public ResponseEntity<Void> markAsUnread(@PathVariable("id") Long id) {
        notificationService.markAsUnread(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
