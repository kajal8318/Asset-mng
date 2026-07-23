package com._eq.asset_management_system.user.dto;

import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.Role;
import lombok.Data;

@Data
public class UserResponseDto extends BaseEntity {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private Role role;

    private Boolean isActive;
}
