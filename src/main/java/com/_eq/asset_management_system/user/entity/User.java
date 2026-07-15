package com._eq.asset_management_system.user.entity;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.Role;
import com._eq.asset_management_system.employee.entity.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	@JoinColumn(name = "employee_id")
	private Employee employee;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isActive;

}
