package com._eq.asset_management_system.assignment.service.impl;

import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.asset.repository.AssetRepository;
import com._eq.asset_management_system.assignment.dto.AssignmentRequestDto;
import com._eq.asset_management_system.assignment.dto.AssignmentResponseDto;
import com._eq.asset_management_system.assignment.entity.AssetAssignment;
import com._eq.asset_management_system.assignment.mapper.AssignmentMapper;
import com._eq.asset_management_system.assignment.repository.AssignmentRepository;
import com._eq.asset_management_system.assignment.service.AssignmentService;
import com._eq.asset_management_system.common.enums.AssignmentStatus;
import com._eq.asset_management_system.common.enums.EmployeeStatus;
import com._eq.asset_management_system.common.enums.AssetStatus;
import com._eq.asset_management_system.common.exception.AlreadyExistsException;
import com._eq.asset_management_system.common.exception.BadRequestException;
import com._eq.asset_management_system.common.exception.ResourceNotFoundException;
import com._eq.asset_management_system.employee.entity.Employee;
import com._eq.asset_management_system.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final EmployeeRepository employeeRepository;

    private final AssetRepository assetRepository;

    private final AssignmentRepository assignmentRepository;

    private final AssignmentMapper assignmentMapper;


    @Override
    public AssignmentResponseDto assignAsset(AssignmentRequestDto request) {
        Employee employee = employeeRepository
                .findByIdAndStatus(
                        request.getEmployeeId(),
                        EmployeeStatus.ACTIVE)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        Asset asset = assetRepository
                .findByIdAndStatusNot(
                        request.getAssetId(),
                        AssetStatus.RETIRED)
                .orElseThrow(() ->
                 new ResourceNotFoundException("Asset not found"));

        if (asset.getStatus() != AssetStatus.AVAILABLE) {

            throw new BadRequestException("Asset is not available");
        }

        AssetAssignment assignment =
                assignmentMapper.toEntity(
                        request,
                        employee,
                        asset);

        AssetAssignment savedAssignment =
                assignmentRepository.save(assignment);

        asset.setStatus(AssetStatus.ASSIGNED);

        assetRepository.save(asset);

        return assignmentMapper
                .toResponseDto(savedAssignment);

    }

    @Override
    public List<AssignmentResponseDto> getAllAssignments() {
        List<AssetAssignment> assignments = assignmentRepository.findAll();

        List<AssignmentResponseDto> responseList = new ArrayList<>();
        for (AssetAssignment assignment : assignments) {
            assignmentMapper.toResponseDto(assignment);

            responseList.add(
                    assignmentMapper.toResponseDto(assignment));

        }
        return responseList;
    }

    @Override
    public AssignmentResponseDto getAssignmentById(Long id) {

        AssetAssignment assignment = assignmentRepository
                .findById(id)
                .orElseThrow(() ->
                 new ResourceNotFoundException("Assignment not found"));

        return assignmentMapper.toResponseDto(assignment);
    }

    @Override
    public AssignmentResponseDto updateAssignment(Long id, AssignmentRequestDto request) {
        AssetAssignment assetAssignment = assignmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));
        assignmentMapper.updateEntity(request, assetAssignment);

       AssetAssignment updateAssignment= assignmentRepository.save(assetAssignment);

        return assignmentMapper.toResponseDto(updateAssignment);


    }

    @Override
    public void returnAsset(Long assignmentId) {
        AssetAssignment assignment = assignmentRepository
                .findById(assignmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));

        if (assignment.getStatus() == AssignmentStatus.RETURNED) {

            throw new AlreadyExistsException("Asset already returned");

        }

    }
}
