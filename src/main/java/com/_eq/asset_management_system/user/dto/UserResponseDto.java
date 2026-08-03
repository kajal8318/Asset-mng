package com._eq.asset_management_system.user.dto;

import com._eq.asset_management_system.common.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String employeeEmail;

    private String firebaseUid;

    private Role role;

    private Boolean isActive;
}