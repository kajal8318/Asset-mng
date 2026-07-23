package com._eq.asset_management_system.assignment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
    @Getter
    @Setter
    public class AssignmentRequestDto {

        private Long employeeId;

        private Long assetId;

        private LocalDate expectedReturnDate;

        private String remarks;

    }

