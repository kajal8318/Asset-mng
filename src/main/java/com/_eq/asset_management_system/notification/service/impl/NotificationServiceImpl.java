package com._eq.asset_management_system.notification.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com._eq.asset_management_system.common.enums.NotificationType;
import com._eq.asset_management_system.notification.dto.NotificationResponseDto;
import com._eq.asset_management_system.notification.entity.Notification;
import com._eq.asset_management_system.notification.repository.NotificationRepository;
import com._eq.asset_management_system.notification.service.NotificationService;
import com._eq.asset_management_system.notification.sse.SseEmitterService;
import com._eq.asset_management_system.user.entity.User;
import com._eq.asset_management_system.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final SseEmitterService sseEmitterService;

    private final UserRepository userRepository;

    @Override
    public NotificationResponseDto createNotification(
            User user,
            String title,
            String message,
            NotificationType type
    ) {

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setRead(false);

        Notification savedNotification =
                notificationRepository.save(notification);

        NotificationResponseDto response =
                mapToDto(savedNotification);

        // Send real-time notification through SSE
        sseEmitterService.sendNotification(
                user.getId(),
                response
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDto> getMyNotifications() {

        Long userId = getCurrentUserId();

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDto> getUnreadNotifications() {

        Long userId = getCurrentUserId();

        return notificationRepository
                .findByUserIdAndReadFalseOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public long getUnreadCount() {

        Long userId = getCurrentUserId();

        return notificationRepository
                .countByUserIdAndReadFalse(userId);
    }

    @Override
    public void markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository
                        .findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"
                                )
                        );

        notification.setRead(true);
    }

    @Override
    public void markAllAsRead() {

        Long userId = getCurrentUserId();

        List<Notification> notifications =
                notificationRepository
                        .findByUserIdAndReadFalseOrderByCreatedAtDesc(userId);

        notifications.forEach(notification ->
                notification.setRead(true)
        );
    }

    private NotificationResponseDto mapToDto(
            Notification notification
    ) {

        return new NotificationResponseDto(
                notification.getId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getType(),
                notification.isRead(),
                notification.getCreatedAt()
        );
    }

    private Long getCurrentUserId() {

        /*
         * We were going to connect this with the
         * authenticated Firebase user.
         *
         * Keep your existing authentication implementation here.
         */

        throw new UnsupportedOperationException(
                "Implement current user resolution"
        );
    }
}