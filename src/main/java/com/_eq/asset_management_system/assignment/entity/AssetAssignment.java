package com._eq.asset_management_system.assignment.entity;

import java.time.LocalDate;

import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.AssignmentStatus;
import com._eq.asset_management_system.employee.entity.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "asset_assignment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class AssetAssignment extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	private LocalDate assignmentDate;

	private LocalDate expectedReturnDate;

	private LocalDate returnedDate;

	@Enumerated(EnumType.STRING)
	private AssignmentStatus status;

	private String remarks;
	

}
