package com._eq.asset_management_system.assignment.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com._eq.asset_management_system.assignment.dto.AssignmentRequestDto;
import com._eq.asset_management_system.assignment.dto.AssignmentResponseDto;
import com._eq.asset_management_system.assignment.entity.AssetAssignment;
import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.common.enums.AssignmentStatus;
import com._eq.asset_management_system.employee.entity.Employee;

@Component
public class AssignmentMapper {


    public AssetAssignment toEntity(AssignmentRequestDto request,
                                    Employee employee,
                                    Asset asset) {

        AssetAssignment assignment = new AssetAssignment();

        assignment.setEmployee(employee);
        assignment.setAsset(asset);

        assignment.setExpectedReturnDate(request.getExpectedReturnDate());
        assignment.setRemarks(request.getRemarks());


        assignment.setAssignmentDate(LocalDate.now());
        assignment.setStatus(AssignmentStatus.ASSIGNED);

        return assignment;
    }

    public AssignmentResponseDto toResponseDto(AssetAssignment assignment) {

        AssignmentResponseDto response = new AssignmentResponseDto();

        response.setId(assignment.getId());

        response.setEmployeeId(assignment.getEmployee().getId());
        response.setEmployeeName(
                assignment.getEmployee().getFirstName() + " "
                + assignment.getEmployee().getLastName());

        response.setAssetId(assignment.getAsset().getId());
        response.setAssetCode(assignment.getAsset().getAssetCode());
        response.setAssetName(assignment.getAsset().getName());

        response.setAssignmentDate(assignment.getAssignmentDate());
        response.setExpectedReturnDate(assignment.getExpectedReturnDate());
        response.setReturnedDate(assignment.getReturnedDate());

        response.setStatus(assignment.getStatus());
        response.setRemarks(assignment.getRemarks());

        response.setCreatedAt(assignment.getCreatedAt());
        response.setUpdatedAt(assignment.getUpdatedAt());

        return response;
    }


    public void updateEntity(AssignmentRequestDto request,
                             AssetAssignment assignment) {

        assignment.setExpectedReturnDate(request.getExpectedReturnDate());
        assignment.setRemarks(request.getRemarks());

    }

}