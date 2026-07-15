package com._eq.asset_management_system.employee.entity;

import java.time.LocalDate;
import java.util.List;

import com._eq.asset_management_system.assignment.entity.AssetAssignment;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.EmployeeStatus;
import com._eq.asset_management_system.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {
	
	@OneToOne(mappedBy = "employee")
	private User user;
	
	@OneToMany(mappedBy = "employee")
	private List<AssetAssignment> assetAssignments;
	
	@Column(unique = true)
	private String employeeCode;
    
	@NotBlank
	private String firstName;
     
	@NotBlank
	private String lastName;
	
	@Column(unique = true)
	private String email;

	@Size(min = 10, max = 10)
	private String phoneNumber;

	private String department;

	private String designation;

	private LocalDate dateOfJoining;

	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;

	
	
	
}