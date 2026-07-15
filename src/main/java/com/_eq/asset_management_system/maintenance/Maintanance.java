package com._eq.asset_management_system.maintenance;

import java.time.LocalDate;

import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.common.enums.AssignmentStatus;
import com._eq.asset_management_system.common.enums.MaintenanceStatus;

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
@Table(name= "maintenances")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Maintanance extends BaseEntity{
//	@ManyToOne
//	@JoinColumn(name = "asset_id")
//	private Asset asset;
	
	   private String issue;

	    private String description;

	    private LocalDate reportedDate;

	    private LocalDate resolvedDate;

	    @Enumerated(EnumType.STRING)
	    private MaintenanceStatus status;

	    private String remarks;

}
