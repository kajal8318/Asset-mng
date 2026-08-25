package com._eq.asset_management_system.notification.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushSubscriptionRequestDto {

    @NotBlank
    private String endpoint;

    @NotNull
    @Valid
    private PushSubscriptionKeysDto keys;
}
