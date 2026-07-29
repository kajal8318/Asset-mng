package com._eq.asset_management_system.employee.dto;

import java.time.LocalDate;

import com._eq.asset_management_system.common.enums.EmployeeStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeRequestDto {
    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    private LocalDate doj;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must be 10 digits")
    private String phoneNumber;

    private String department;

    private String designation;

    private EmployeeStatus status;
}