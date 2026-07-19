package com._eq.asset_management_system.employee.repository;

import java.util.List;
import java.util.Optional;

import com._eq.asset_management_system.common.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com._eq.asset_management_system.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByIdAndStatus(Long id, EmployeeStatus status);

    List<Employee> findByStatus(EmployeeStatus status);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);
}