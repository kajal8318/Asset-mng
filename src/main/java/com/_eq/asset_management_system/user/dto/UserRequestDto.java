package com._eq.asset_management_system.user.dto;

import com._eq.asset_management_system.common.enums.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequestDto {

    private Long employeeId;

    private String password;

    private Role role;

    private Boolean isActive;
}
