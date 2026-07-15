package com._eq.asset_management_system.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._eq.asset_management_system.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByEmail(String email);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);
}