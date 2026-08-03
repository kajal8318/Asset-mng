package com._eq.asset_management_system.security.util;

import com._eq.asset_management_system.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {

    private Long userId;

    private Long employeeId;

    private String firebaseUid;

    private Role role;
}