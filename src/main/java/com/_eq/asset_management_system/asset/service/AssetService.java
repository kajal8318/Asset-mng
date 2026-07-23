package com._eq.asset_management_system.asset.service;

import java.util.List;

import com._eq.asset_management_system.asset.dto.AssetRequestDto;
import com._eq.asset_management_system.asset.dto.AssetResponseDto;

public interface AssetService {

    AssetResponseDto createAsset(AssetRequestDto request);

    List<AssetResponseDto> getAllAssets();

    AssetResponseDto getAssetById(Long id);

    AssetResponseDto updateAsset(Long id, AssetRequestDto request);

    void deleteAsset(Long id);
}