package com._eq.asset_management_system.employee.dto;

import com._eq.asset_management_system.common.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data

public class EmployeeSearchRequest {
    private String department;

    private String designation;

    private EmployeeStatus status;

    private String name;

    private String employeeCode;

}