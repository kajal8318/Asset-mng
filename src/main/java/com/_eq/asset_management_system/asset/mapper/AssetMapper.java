package com._eq.asset_management_system.asset.mapper;

import com._eq.asset_management_system.asset.dto.AssetRequestDto;
import com._eq.asset_management_system.asset.dto.AssetResponseDto;
import com._eq.asset_management_system.asset.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper {

    public Asset toEntity(AssetRequestDto request){

        Asset asset = new Asset();

        asset.setAssetCode(request.getAssetCode());
        asset.setName(request.getName());
        asset.setSerialNumber(request.getSerialNumber());

        return asset;
    }

    public AssetResponseDto toResponseDto(Asset asset){

        AssetResponseDto response = new AssetResponseDto();

        response.setId(asset.getId());
        response.setAssetCode(asset.getAssetCode());
        response.setName(asset.getName());
        response.setSerialNumber(asset.getSerialNumber());
        response.setStatus(asset.getStatus());
        response.setCreatedAt(asset.getCreatedAt());
        response.setUpdatedAt(asset.getUpdatedAt());

        return response;
    }

    public void updateEntity(AssetRequestDto request, Asset asset){

        asset.setAssetCode(request.getAssetCode());
        asset.setName(request.getName());
        asset.setSerialNumber(request.getSerialNumber());

    }

}