package com._eq.asset_management_system.assignment.contoller;

import com._eq.asset_management_system.assignment.dto.AssignmentRequestDto;
import com._eq.asset_management_system.assignment.dto.AssignmentResponseDto;
import com._eq.asset_management_system.assignment.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@AllArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    // Assign Asset
    @PostMapping
    public AssignmentResponseDto assignAsset(
            @Valid @RequestBody AssignmentRequestDto request) {

        return assignmentService.assignAsset(request);
    }

    // Get All Assignments
    @GetMapping
    public List<AssignmentResponseDto> getAllAssignments() {

        return assignmentService.getAllAssignments();
    }

    // Get Assignment By Id
    @GetMapping("/{id}")
    public AssignmentResponseDto getAssignmentById(
            @PathVariable Long id) {

        return assignmentService.getAssignmentById(id);
    }

    // Update Assignment
    @PutMapping("/{id}")
    public AssignmentResponseDto updateAssignment(
            @Valid @PathVariable Long id,
            @RequestBody AssignmentRequestDto request) {

        return assignmentService.updateAssignment(id, request);
    }

    // Return Asset
    @PutMapping("/{assignmentId}/return")
    public void returnAsset(
            @PathVariable Long assignmentId) {

        assignmentService.returnAsset(assignmentId);
    }
}