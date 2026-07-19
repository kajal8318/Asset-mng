package com._eq.asset_management_system.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com._eq.asset_management_system.common.enums.EmployeeStatus;
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
		employee.setFirstName(request.getFirstName());
		employee.setLastName(request.getLastName());
		employee.setDateOfJoining(request.getDoj());
		employee.setEmail(request.getEmail());
		employee.setPhoneNumber(request.getPhoneNumber());
		employee.setDepartment(request.getDepartment());
		employee.setDesignation(request.getDesignation());
		employee.setStatus(request.getStatus());

		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeResponseDto response = new EmployeeResponseDto();

		response.setId(savedEmployee.getId());
		response.setEmployeeCode(savedEmployee.getEmployeeCode());
		response.setFirstName(employee.getFirstName() );
		response.setLastName(employee.getLastName());
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
	public List<EmployeeResponseDto> getAllEmployees() {

		List<Employee> employees = employeeRepository.findAll();

		List<EmployeeResponseDto> responseList = new ArrayList<>();

		for (Employee employee : employees) {

			EmployeeResponseDto response = new EmployeeResponseDto();

			response.setId(employee.getId());
			response.setEmployeeCode(employee.getEmployeeCode());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setDoj(employee.getDateOfJoining());
			response.setEmail(employee.getEmail());
			response.setPhoneNumber(employee.getPhoneNumber());
			response.setDepartment(employee.getDepartment());
			response.setDesignation(employee.getDesignation());
			response.setStatus(employee.getStatus());
			response.setCreatedAt(employee.getCreatedAt());
			response.setUpdatedAt(employee.getUpdatedAt());


			responseList.add(response);
		}

		return responseList;
	}
	@Override
	public EmployeeResponseDto getEmployeeById(Long id) {

		// Step 1: Find employee by id
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);

		// Step 2: If employee is not found, throw an exception
		Employee employee = optionalEmployee.orElseThrow(
				() -> new RuntimeException("Employee not found with id: " + id)
		);

		// Step 3: Create Response DTO
		EmployeeResponseDto response = new EmployeeResponseDto();

		// Step 4: Map Entity to Response DTO
		response.setId(employee.getId());
		response.setEmployeeCode(employee.getEmployeeCode());
		response.setFirstName(employee.getFirstName());
		response.setLastName(employee.getLastName());
		response.setDoj(employee.getDateOfJoining());
		response.setEmail(employee.getEmail());
		response.setPhoneNumber(employee.getPhoneNumber());
		response.setDepartment(employee.getDepartment());
		response.setDesignation(employee.getDesignation());
		response.setStatus(employee.getStatus());
		response.setCreatedAt(employee.getCreatedAt());
		response.setUpdatedAt(employee.getUpdatedAt());

		// Step 5: Return response
		return response;
	}

	@Override
	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {
	// Check if employee exists
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

			// Check duplicate employee code
			if (!employee.getEmployeeCode().equals(request.getEmployeeCode())
					&& employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
				throw new RuntimeException("Employee code already exists");
			}

			// Check duplicate email
			if (!employee.getEmail().equals(request.getEmail())
					&& employeeRepository.existsByEmail(request.getEmail())) {
				throw new RuntimeException("Email already exists");
			}

			// Update fields
			employee.setEmployeeCode(request.getEmployeeCode());
			employee.setFirstName(request.getFirstName());
			employee.setLastName(request.getLastName());
			employee.setDateOfJoining(request.getDoj());
			employee.setEmail(request.getEmail());
			employee.setPhoneNumber(request.getPhoneNumber());
			employee.setDepartment(request.getDepartment());
			employee.setDesignation(request.getDesignation());
			employee.setStatus(request.getStatus());

			// Save updated employee
			Employee updatedEmployee = employeeRepository.save(employee);

			// Prepare response
			EmployeeResponseDto response = new EmployeeResponseDto();

			response.setId(updatedEmployee.getId());
			response.setEmployeeCode(updatedEmployee.getEmployeeCode());
			response.setFirstName(updatedEmployee.getFirstName());
			response.setLastName(updatedEmployee.getLastName());
			response.setDoj(updatedEmployee.getDateOfJoining());
			response.setEmail(updatedEmployee.getEmail());
			response.setPhoneNumber(updatedEmployee.getPhoneNumber());
			response.setDepartment(updatedEmployee.getDepartment());
			response.setDesignation(updatedEmployee.getDesignation());
			response.setStatus(updatedEmployee.getStatus());
			response.setCreatedAt(updatedEmployee.getCreatedAt());
			response.setUpdatedAt(updatedEmployee.getUpdatedAt());

			return response;
		}

	@Override
	public void deleteEmployee(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() ->
						new RuntimeException("Employee not found with id: " + id));

		employee.setStatus(EmployeeStatus.RESIGNED);

		employeeRepository.save(employee);
	}

}