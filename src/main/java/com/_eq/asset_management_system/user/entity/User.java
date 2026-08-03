package com._eq.asset_management_system.user.entity;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.Role;
import com._eq.asset_management_system.employee.entity.Employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = "Employee Id is required")
    private Employee employee;

    @Column(name = "firebase_uid", unique = true, nullable = false)
    private String firebaseUid;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Role role;

    private Boolean isActive;
}
