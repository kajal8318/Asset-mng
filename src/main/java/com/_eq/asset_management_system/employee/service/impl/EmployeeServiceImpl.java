package com._eq.asset_management_system.employee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com._eq.asset_management_system.employee.dto.EmployeeRequestDto;
import com._eq.asset_management_system.employee.dto.EmployeeResponseDto;
import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.employee.repository.EmployeeRepository;
import com._eq.asset_management_system.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

	@Override
	public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
		if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
		    throw new RuntimeException("Employee code already exists");
		}

		if (employeeRepository.existsByEmail(request.getEmail())) {
		    throw new RuntimeException("Email already exists");
		}
		Employee employee = new Employee();

		employee.setEmployeeCode(request.getEmployeeCode());
		employee.setFirstName(request.getName());
		employee.setLastName(request.getName());
		employee.setDateOfJoining(request.getDoj());
		employee.setEmail(request.getEmail());
		employee.setPhoneNumber(request.getPhoneNumber());
		employee.setDepartment(request.getDepartment());
		employee.setDesignation(request.getDesignation());
		employee.setStatus(request.getStatus());
		return EmployeeResponseDto;
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeResponseDto response = new EmployeeResponseDto();

		response.setId(savedEmployee.getId());
		response.setEmployeeCode(savedEmployee.getEmployeeCode());
		response.setName(savedEmployee.getFirstName());
		response.setName(savedEmployee.getLastName());
		response.setDoj(savedEmployee.getDateOfJoining());
		response.setEmail(savedEmployee.getEmail());
		response.setPhoneNumber(savedEmployee.getPhoneNumber());
		response.setDepartment(savedEmployee.getDepartment());
		response.setDesignation(savedEmployee.getDesignation());
		response.setStatus(savedEmployee.getStatus());
		response.setCreatedAt(savedEmployee.getCreatedAt());
		response.setUpdatedAt(savedEmployee.getUpdatedAt());

		return response;
	}

	@Override
	public EmployeeResponseDto getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeResponseDto> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		
	}

}