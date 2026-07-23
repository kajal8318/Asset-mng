package com._eq.asset_management_system.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com._eq.asset_management_system.asset.dto.AssetRequestDto;
import com._eq.asset_management_system.asset.dto.AssetResponseDto;
import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.asset.mapper.AssetMapper;
import com._eq.asset_management_system.asset.repository.AssetRepository;
import com._eq.asset_management_system.asset.service.AssetService;
import com._eq.asset_management_system.common.enums.AssetStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    private final AssetMapper assetMapper;

    @Override
    public AssetResponseDto createAsset(AssetRequestDto request) {

        // Duplicate Asset Code
        if (assetRepository.existsByAssetCode(request.getAssetCode())) {
            throw new RuntimeException("Asset code already exists");
        }

        // Duplicate Serial Number
        if (assetRepository.existsBySerialNumber(request.getSerialNumber())) {
            throw new RuntimeException("Serial number already exists");
        }

        Asset asset = assetMapper.toEntity(request);

        // Every new asset is AVAILABLE
        asset.setStatus(AssetStatus.AVAILABLE);

        Asset savedAsset = assetRepository.save(asset);

        return assetMapper.toResponseDto(savedAsset);
    }

    @Override
    public List<AssetResponseDto> getAllAssets() {

        List<Asset> assets =
                assetRepository.findByStatusNot(AssetStatus.RETIRED);

        List<AssetResponseDto> responseList = new ArrayList<>();

        for (Asset asset : assets) {

            responseList.add(assetMapper.toResponseDto(asset));

        }

        return responseList;
    }

    @Override
    public AssetResponseDto getAssetById(Long id) {

        Asset asset = assetRepository
                .findByIdAndStatusNot(id, AssetStatus.RETIRED)
                .orElseThrow(() ->
                        new RuntimeException("Asset not found"));

        return assetMapper.toResponseDto(asset);
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetRequestDto request) {

        Asset asset = assetRepository
                .findByIdAndStatusNot(id, AssetStatus.RETIRED)
                .orElseThrow(() ->
                        new RuntimeException("Asset not found"));

        // Duplicate Asset Code
        if (!asset.getAssetCode().equals(request.getAssetCode())
                && assetRepository.existsByAssetCode(request.getAssetCode())) {

            throw new RuntimeException("Asset code already exists");
        }

        // Duplicate Serial Number
        if (!asset.getSerialNumber().equals(request.getSerialNumber())
                && assetRepository.existsBySerialNumber(request.getSerialNumber())) {

            throw new RuntimeException("Serial number already exists");
        }

        assetMapper.updateEntity(request, asset);

        Asset savedAsset = assetRepository.save(asset);

        return assetMapper.toResponseDto(savedAsset);
    }

    @Override
    public void deleteAsset(Long id) {

        Asset asset = assetRepository
                .findByIdAndStatusNot(id, AssetStatus.RETIRED)
                .orElseThrow(() ->
                        new RuntimeException("Asset not found"));

        asset.setStatus(AssetStatus.RETIRED);

        assetRepository.save(asset);
    }
}
