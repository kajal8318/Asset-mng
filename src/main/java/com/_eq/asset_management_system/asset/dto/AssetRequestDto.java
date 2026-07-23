package com._eq.asset_management_system.asset.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetRequestDto {

    private String assetCode;

    private String name;

    private String serialNumber;

}