package com._eq.asset_management_system.user.dto;

import com._eq.asset_management_system.common.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UpdateUserRequestDto {


    @NotNull(message = "Role is required")
    private Role role;
}
