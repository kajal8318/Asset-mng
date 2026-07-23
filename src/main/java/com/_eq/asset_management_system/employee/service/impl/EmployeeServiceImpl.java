package com._eq.asset_management_system.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com._eq.asset_management_system.common.enums.EmployeeStatus;
import com._eq.asset_management_system.employee.mapper.EmployeeMapper;
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

	private final EmployeeMapper employeeMapper;

	@Override
	public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
		if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
		    throw new RuntimeException("Employee code already exists");
		}

		if (employeeRepository.existsByEmail(request.getEmail())) {
		    throw new RuntimeException("Email already exists");
		}
		Employee employee = employeeMapper.toEntity(request);

		Employee savedEmployee = employeeRepository.save(employee);

		return employeeMapper.toResponseDto(savedEmployee);

	}





	@Override
	public List<EmployeeResponseDto> getAllEmployees() {

		List<Employee> employees = employeeRepository.findByStatus(EmployeeStatus.ACTIVE);

		List<EmployeeResponseDto> responseList = new ArrayList<>();

		for(Employee employee : employees){

			responseList.add(employeeMapper.toResponseDto(employee));

		}

		return responseList;
	}
	@Override
	public EmployeeResponseDto getEmployeeById(Long id) {

		Employee employee = employeeRepository
				.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(() ->
						new RuntimeException("Active employee not found with id: " + id));

		// Step 3: Create Response DTO
		return employeeMapper.toResponseDto(employee);
	}

	@Override
	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {
	// Check if employee exists
		Employee employee = employeeRepository
				.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(() ->
						new RuntimeException("Active employee not found with id: " + id));

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

		employeeMapper.updateEntity(request, employee);

		Employee savedEmployee = employeeRepository.save(employee);

		return employeeMapper.toResponseDto(savedEmployee);
		}

	@Override
	public void deleteEmployee(Long id) {
		Employee employee = employeeRepository
				.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(() ->
						new RuntimeException("Active employee not found with id: " + id));

		employee.setStatus(EmployeeStatus.RESIGNED);

		employeeRepository.save(employee);
	}

}