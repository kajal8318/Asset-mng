package com._eq.asset_management_system.notification.dto;

import java.time.LocalDateTime;

import com._eq.asset_management_system.common.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {

    private Long id;

    private String title;

    private String message;

    private NotificationType type;

    private boolean read;

    private LocalDateTime createdAt;
}