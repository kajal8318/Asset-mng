package com._eq.asset_management_system.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushSubscriptionKeysDto {

    @NotBlank
    private String p256dh;

    @NotBlank
    private String auth;
}