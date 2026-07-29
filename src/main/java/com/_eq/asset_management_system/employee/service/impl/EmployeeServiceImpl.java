package com._eq.asset_management_system.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com._eq.asset_management_system.common.dto.PageResponse;
import com._eq.asset_management_system.common.enums.EmployeeStatus;
import com._eq.asset_management_system.common.exception.AlreadyExistsException;
import com._eq.asset_management_system.common.exception.ResourceNotFoundException;
import com._eq.asset_management_system.employee.dto.EmployeeSearchRequest;
import com._eq.asset_management_system.employee.mapper.EmployeeMapper;
import com._eq.asset_management_system.employee.specification.EmployeeSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com._eq.asset_management_system.employee.dto.EmployeeRequestDto;
import com._eq.asset_management_system.employee.dto.EmployeeResponseDto;
import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.employee.repository.EmployeeRepository;
import com._eq.asset_management_system.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

	private final EmployeeMapper employeeMapper;

	@Override
	public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
		log.info("Creating employee with email {}", request.getEmail());
		if (employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
			log.warn("Employee already exists with email {}", request.getEmail());
			throw new AlreadyExistsException("Employee code already exists");
		}

		if (employeeRepository.existsByEmail(request.getEmail())) {
			throw new AlreadyExistsException("Email already exists");
		}
		Employee employee = employeeMapper.toEntity(request);

		Employee savedEmployee = employeeRepository.save(employee);

		log.info("Employee created successfully with id {}", savedEmployee.getId());

		return employeeMapper.toResponseDto(savedEmployee);
	}
		@Override
		public PageResponse<EmployeeResponseDto> getAllEmployees(
				EmployeeSearchRequest request,
		        int page,
				int size,
				String sortBy,
				String direction
				) {

			// Create Sort object
			log.info("Direction = {}", direction);
			log.info("Sort By = {}", sortBy);
			Sort sort = Sort.by(
					Sort.Direction.fromString(direction),
					sortBy);

			// Create Pageable object
			Pageable pageable = PageRequest.of(page, size, sort);

			// Start with an empty Specification
			Specification<Employee> specification =
					(root, query, cb) -> cb.conjunction();

			// Filter by Status
			if (request.getStatus() != null) {
				specification = specification.and(
						EmployeeSpecification.hasStatus(request.getStatus()));
			}

			// Filter by Department
			if (request.getDepartment() != null &&
					!request.getDepartment().isBlank()) {

				specification = specification.and(
						EmployeeSpecification.hasDepartment(
								request.getDepartment()));
			}

			// Filter by Designation
			if (request.getDesignation() != null &&
					!request.getDesignation().isBlank()) {

				specification = specification.and(
						EmployeeSpecification.hasDesignation(
								request.getDesignation()));
			}

			// Filter by Name
			if (request.getName() != null &&
					!request.getName().isBlank()) {

				specification = specification.and(
						EmployeeSpecification.nameContains(
								request.getName()));
			}

			// Filter by Employee Code
			if (request.getEmployeeCode() != null &&
					!request.getEmployeeCode().isBlank()) {

				specification = specification.and(
						EmployeeSpecification.hasEmployeeCode(
								request.getEmployeeCode()));
			}

			// Execute Query
			Page<Employee> employeePage =
					employeeRepository.findAll(specification, pageable);

			// Convert Entity to DTO
			List<EmployeeResponseDto> responseList = employeePage
					.getContent()
					.stream()
					.map(employeeMapper::toResponseDto)
					.toList();

			// Prepare Page Response
			PageResponse<EmployeeResponseDto> response = new PageResponse<>();

			response.setContent(responseList);
			response.setPageNumber(employeePage.getNumber());
			response.setPageSize(employeePage.getSize());
			response.setTotalElements(employeePage.getTotalElements());
			response.setTotalPages(employeePage.getTotalPages());
			response.setLast(employeePage.isLast());

			return response;
		}
	@Override
	public EmployeeResponseDto getEmployeeById(Long id) {

		Employee employee = employeeRepository
				.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(() ->
						new ResourceNotFoundException("Active Employee not found"));

		// Step 3: Create Response DTO
		return employeeMapper.toResponseDto(employee);
	}

	@Override
	public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {
	// Check if employee exists
		Employee employee = employeeRepository
				.findByIdAndStatus(id, EmployeeStatus.ACTIVE)
				.orElseThrow(() ->
						new ResourceNotFoundException("Active Employee not found"));

			// Check duplicate employee code
			if (!employee.getEmployeeCode().equals(request.getEmployeeCode())
					&& employeeRepository.existsByEmployeeCode(request.getEmployeeCode())) {
				 throw new AlreadyExistsException("Employee code already exists");
			}

			// Check duplicate email
			if (!employee.getEmail().equals(request.getEmail())
					&& employeeRepository.existsByEmail(request.getEmail())) {
				throw new AlreadyExistsException("Email already exists");
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
						new ResourceNotFoundException("Active Employee not found"));

		employee.setStatus(EmployeeStatus.RESIGNED);

		employeeRepository.save(employee);
	}

}