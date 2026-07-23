package com._eq.asset_management_system.employee.controller;

import com._eq.asset_management_system.employee.dto.EmployeeRequestDto;
import com._eq.asset_management_system.employee.dto.EmployeeResponseDto;

import com._eq.asset_management_system.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDto createEmployee(@RequestBody EmployeeRequestDto request){
        return employeeService.createEmployee(request);
    }
    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")

    public EmployeeResponseDto getEmployeeById(@PathVariable Long id)
    {
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Long id,
                                              @RequestBody EmployeeRequestDto request) {

        return employeeService.updateEmployee(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee deleted successfully");
    }

   }



