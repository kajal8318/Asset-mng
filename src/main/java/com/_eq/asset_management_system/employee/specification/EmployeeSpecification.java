package com._eq.asset_management_system.employee.specification;

import com._eq.asset_management_system.common.enums.EmployeeStatus;
import com._eq.asset_management_system.employee.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmployeeSpecification {


    public static Specification<Employee> hasStatus(EmployeeStatus status) {

        return (root, query, cb) -> {

            if(status == null) {
                return null;
            }

            return cb.equal(
                    root.get("status"),
                    status
            );
        };
    }


    public static Specification<Employee> hasDepartment(String department) {

        return (root, query, cb) -> {

            if(department == null) {
                return null;
            }

            return cb.equal(
                    root.get("department"),
                    department
            );
        };
    }


    public static Specification<Employee> hasDesignation(String designation) {

        return (root, query, cb) -> {

            if(designation == null) {
                return null;
            }

            return cb.equal(
                    root.get("designation"),
                    designation
            );
        };
    }


    public static Specification<Employee> nameContains(String name) {

        return (root, query, cb) -> {

            if (name == null || name.isBlank()) {
                return null;
            }

            return cb.or(
                    cb.like(
                            cb.lower(root.get("firstName")),
                            "%" + name.toLowerCase() + "%"
                    ),
                    cb.like(
                            cb.lower(root.get("lastName")),
                            "%" + name.toLowerCase() + "%"
                    )
            );

        };
    }

    public static Specification<Employee> emailContains(String email) {

        return (root, query, cb) -> {

            if (email == null || email.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("email")),
                    "%" + email.toLowerCase() + "%"
            );
        };
    }
    public static Specification<Employee> hasEmployeeCode(String employeeCode) {

        return (root, query, cb) -> {

            if (employeeCode == null || employeeCode.isBlank()) {
                return null;
            }

            return cb.equal(root.get("employeeCode"), employeeCode);
        };
    }

    public static Specification<Employee> phoneContain(String phone) {
        return (root, query, cb) -> {
            if (phone == null || phone.isBlank()) {
                return null;
            }
            return cb.like( root.get("phoneNumber"), "%" + phone + "%");
        };
    }
    public static Specification<Employee> joinedBefore(LocalDate date) {

        return (root, query, cb) -> {

            if (date == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get("joiningDate"), date);
        };
    }
    public static Specification<Employee> hasId(Long id) {

        return (root, query, cb) -> {

            if (id == null) {
                return null;
            }

            return cb.equal(root.get("id"), id);
        };
    }
}


