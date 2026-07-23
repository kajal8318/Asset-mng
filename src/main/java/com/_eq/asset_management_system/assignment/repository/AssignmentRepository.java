package com._eq.asset_management_system.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._eq.asset_management_system.assignment.entity.AssetAssignment;
import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.common.enums.AssignmentStatus;
import com._eq.asset_management_system.employee.entity.Employee;

public interface AssignmentRepository extends JpaRepository<AssetAssignment, Long> {

    List<AssetAssignment> findByStatus(AssignmentStatus status);

    Optional<AssetAssignment> findByIdAndStatus(Long id, AssignmentStatus status);

    Optional<AssetAssignment> findByAssetAndStatus(Asset asset,
                                                   AssignmentStatus status);

    List<AssetAssignment> findByEmployee(Employee employee);

}
