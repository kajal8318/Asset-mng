package com._eq.asset_management_system.notification.dto;

import com._eq.asset_management_system.common.enums.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationCreateDto {

    private Long userId;

    private String title;

    private String message;

    private NotificationType type;
}