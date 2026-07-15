package com._eq.asset_management_system.employee.service;

import java.util.List;

import com._eq.asset_management_system.employee.dto.EmployeeRequestDto;
import com._eq.asset_management_system.employee.dto.EmployeeResponseDto;



public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto request);

    EmployeeResponseDto getEmployeeById(Long id);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request);

    void deleteEmployee(Long id);
}