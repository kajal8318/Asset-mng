package com._eq.asset_management_system.employee.dto;

import java.time.LocalDate;

import com._eq.asset_management_system.common.enums.EmployeeStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmployeeRequestDto {

    private String employeeCode;

    private String firstName;

    private String lastName;

    private LocalDate doj;

    private String email;

    private String phoneNumber;

    private String department;

    private String designation;

    private EmployeeStatus status;
}