package com._eq.asset_management_system.asset.service;

import java.util.List;

import com._eq.asset_management_system.asset.dto.AssetRequestDto;
import com._eq.asset_management_system.asset.dto.AssetResponseDto;
import com._eq.asset_management_system.asset.dto.AssetSearchRequest;
import com._eq.asset_management_system.common.dto.PageResponse;

public interface AssetService {

    AssetResponseDto createAsset(AssetRequestDto request);

    PageResponse<AssetResponseDto> getAllAssets(
            AssetSearchRequest request,
            int page,
            int size,
            String direction,
            String sortBy);

    AssetResponseDto getAssetById(Long id);

    AssetResponseDto updateAsset(Long id, AssetRequestDto request);

    void deleteAsset(Long id);
}