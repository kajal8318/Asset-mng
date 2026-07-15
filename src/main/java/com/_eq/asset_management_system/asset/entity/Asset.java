package com._eq.asset_management_system.asset.entity;

import java.util.List;

import com._eq.asset_management_system.assignment.entity.AssetAssignment;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.AssetStatus;
import com._eq.asset_management_system.common.enums.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="assets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset extends BaseEntity{
	
	@OneToMany(mappedBy = "asset")
	private List<AssetAssignment> assetAssignments;
    
	@Column(unique = true)
	private String assetCode;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true)
	private String serialNumber;
	
	@Enumerated(EnumType.STRING)
	private AssetStatus status;
	

}
