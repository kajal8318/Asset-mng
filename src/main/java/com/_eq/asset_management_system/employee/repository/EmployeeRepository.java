package com._eq.asset_management_system.employee.repository;

import java.util.List;
import java.util.Optional;

import com._eq.asset_management_system.common.enums.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com._eq.asset_management_system.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {


    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByEmail(String email);

    Page<Employee> findByStatus(EmployeeStatus status, Pageable pageable);


    Optional<Employee> findByIdAndStatus(Long id, EmployeeStatus status);

    boolean existsByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);
}