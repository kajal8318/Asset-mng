package com._eq.asset_management_system.assignment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
    @Getter
    @Setter
    public class AssignmentRequestDto {

        @NotNull(message = "Employee id is required")
        private Long employeeId;

        @NotNull(message = "Asset id is required")
        private Long assetId;

        @NotNull(message = "Expected return date is required")
        private LocalDate expectedReturnDate;

        @Size(max = 255, message = "Remarks cannot exceed 255 characters")
        private String remarks;

    }

