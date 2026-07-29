package com._eq.asset_management_system.employee.service;

import java.util.List;

import com._eq.asset_management_system.common.dto.PageResponse;
import com._eq.asset_management_system.common.enums.EmployeeStatus;
import com._eq.asset_management_system.employee.dto.EmployeeRequestDto;
import com._eq.asset_management_system.employee.dto.EmployeeResponseDto;
import com._eq.asset_management_system.employee.dto.EmployeeSearchRequest;


public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto request);

    EmployeeResponseDto getEmployeeById(Long id);

    PageResponse<EmployeeResponseDto> getAllEmployees(
            EmployeeSearchRequest request,
            int page,
            int size,
            String direction,
            String sortBy);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request);

    void deleteEmployee(Long id);
}