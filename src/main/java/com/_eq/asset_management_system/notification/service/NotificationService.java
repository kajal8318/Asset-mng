package com._eq.asset_management_system.notification.service;

import java.util.List;

import com._eq.asset_management_system.common.enums.NotificationType;
import com._eq.asset_management_system.notification.dto.NotificationCreateDto;
import com._eq.asset_management_system.notification.dto.NotificationResponseDto;
import com._eq.asset_management_system.user.entity.User;

public interface NotificationService {

    NotificationResponseDto createNotification(
            User user,
            String title,
            String message,
            NotificationType type
    );

    List<NotificationResponseDto> getMyNotifications();

    List<NotificationResponseDto> getUnreadNotifications();

    long getUnreadCount();

    void markAsRead(Long notificationId);

    void markAllAsRead();

    void broadcastNotification(NotificationCreateDto request);
}