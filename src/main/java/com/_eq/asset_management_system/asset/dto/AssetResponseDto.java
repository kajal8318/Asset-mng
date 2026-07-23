package com._eq.asset_management_system.asset.dto;

import java.time.LocalDateTime;

import com._eq.asset_management_system.common.enums.AssetStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetResponseDto {

    private Long id;

    private String assetCode;

    private String name;

    private String serialNumber;

    private AssetStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}