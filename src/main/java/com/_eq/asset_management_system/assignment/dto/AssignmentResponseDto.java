package com._eq.asset_management_system.assignment.dto;

import com._eq.asset_management_system.common.enums.AssignmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssignmentResponseDto {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private Long assetId;

    private String assetCode;

    private String assetName;

    private LocalDate assignmentDate;

    private LocalDate expectedReturnDate;

    private LocalDate returnedDate;

    private AssignmentStatus status;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}