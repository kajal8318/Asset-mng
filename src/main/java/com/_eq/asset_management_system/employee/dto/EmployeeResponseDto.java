package com._eq.asset_management_system.employee.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com._eq.asset_management_system.common.enums.EmployeeStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeResponseDto {

    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private LocalDate doj;

    private String email;

    private String phoneNumber;

    private String department;

    private String designation;

    private EmployeeStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}