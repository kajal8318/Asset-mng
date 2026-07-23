package com._eq.asset_management_system.assignment.service;

import java.util.List;

import com._eq.asset_management_system.assignment.dto.AssignmentRequestDto;
import com._eq.asset_management_system.assignment.dto.AssignmentResponseDto;

public interface AssignmentService {

    AssignmentResponseDto assignAsset(AssignmentRequestDto request);

    List<AssignmentResponseDto> getAllAssignments();

    AssignmentResponseDto getAssignmentById(Long id);

    AssignmentResponseDto updateAssignment(Long id,
                                           AssignmentRequestDto request);

    void returnAsset(Long assignmentId);

}
