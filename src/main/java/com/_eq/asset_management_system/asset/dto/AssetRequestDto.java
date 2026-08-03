package com._eq.asset_management_system.asset.dto;

import com._eq.asset_management_system.common.enums.AssetStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssetRequestDto {

    @NotBlank(message = "Asset code is required")
    @Size(max = 20, message = "Asset code cannot exceed 20 characters")
    private String assetCode;

    @NotBlank(message = "Asset name is required")
    @Size(max = 100, message = "Asset name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Serial number is required")
    @Size(max = 50, message = "Serial number cannot exceed 50 characters")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    private LocalDate purchaseDate;

    private String category;



}