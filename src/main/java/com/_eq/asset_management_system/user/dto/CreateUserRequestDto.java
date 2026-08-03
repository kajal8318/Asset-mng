package com._eq.asset_management_system.user.dto;

import com._eq.asset_management_system.common.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {

    @NotNull(message = "Employee Id is required")
    private Long employeeId;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one number and one special character."
    )
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}