package com._eq.asset_management_system.asset.dto;

import com._eq.asset_management_system.common.enums.AssetStatus;
import lombok.Data;

@Data
public class AssetSearchRequest {
    private String assetCode;

    private String assetName;

    private String serialNumber;

    private String category;

    private AssetStatus status;
}
