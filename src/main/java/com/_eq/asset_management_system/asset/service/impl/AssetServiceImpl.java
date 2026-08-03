package com._eq.asset_management_system.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import com._eq.asset_management_system.asset.dto.AssetSearchRequest;
import com._eq.asset_management_system.asset.specification.AssetSpecification;
import com._eq.asset_management_system.common.dto.PageResponse;
import com._eq.asset_management_system.common.exception.AlreadyExistsException;
import com._eq.asset_management_system.common.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
            throw new AlreadyExistsException("Asset code already exists");
        }

        // Duplicate Serial Number
        if (assetRepository.existsBySerialNumber(request.getSerialNumber())) {
            throw new AlreadyExistsException("Serial number already exists");
        }

        Asset asset = assetMapper.toEntity(request);

        // Every new asset is AVAILABLE
        asset.setStatus(AssetStatus.AVAILABLE);

        Asset savedAsset = assetRepository.save(asset);

        return assetMapper.toResponseDto(savedAsset);
    }

    @Override
    public PageResponse<AssetResponseDto> getAllAssets(
            AssetSearchRequest request,
            int page,
            int size,
            String direction,
            String sortBy) {

        // Create Sort object
        Sort sort = Sort.by(
                Sort.Direction.fromString(direction),
                sortBy);

        // Create Pageable object
        Pageable pageable = PageRequest.of(page, size, sort);

        // Start with an empty Specification
        Specification<Asset> specification =
                (root, query, cb) -> cb.conjunction();

        // Filter by Asset Code
        specification = specification.and(
                AssetSpecification.hasAssetCode(
                        request.getAssetCode()));

        // Filter by Asset Name
        specification = specification.and(
                AssetSpecification.assetNameContains(
                        request.getAssetName()));

        // Filter by Serial Number
        specification = specification.and(
                AssetSpecification.hasSerialNumber(
                        request.getSerialNumber()));

        // Filter by Category
        specification = specification.and(
                AssetSpecification.hasCategory(
                        request.getCategory()));

        // Filter by Status
        specification = specification.and(
                AssetSpecification.hasStatus(
                        request.getStatus()));

        // Fetch data
        Page<Asset> assetPage =
                assetRepository.findAll(
                        specification,
                        pageable);

        // Convert Entity Page to DTO Page
        Page<AssetResponseDto> responsePage =
                assetPage.map(assetMapper::toResponseDto);

        // Prepare custom page response
        PageResponse<AssetResponseDto> response =
                new PageResponse<>();

        response.setContent(responsePage.getContent());
        response.setPageNumber(responsePage.getNumber());
        response.setPageSize(responsePage.getSize());
        response.setTotalElements(responsePage.getTotalElements());
        response.setTotalPages(responsePage.getTotalPages());
        response.setLast(responsePage.isLast());

        return response;
    }

    @Override
    public AssetResponseDto getAssetById(Long id) {

        Asset asset = assetRepository
                .findByIdAndStatusNot(id, AssetStatus.RETIRED)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found"));

        return assetMapper.toResponseDto(asset);
    }

    @Override
    public AssetResponseDto updateAsset(Long id, AssetRequestDto request) {

        Asset asset = assetRepository
                .findByIdAndStatusNot(id, AssetStatus.RETIRED)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found"));

        // Duplicate Asset Code
        if (!asset.getAssetCode().equals(request.getAssetCode())
                && assetRepository.existsByAssetCode(request.getAssetCode())) {

            throw new AlreadyExistsException("Asset code already exists");
        }

        // Duplicate Serial Number
        if (!asset.getSerialNumber().equals(request.getSerialNumber())
                && assetRepository.existsBySerialNumber(request.getSerialNumber())) {

            throw new AlreadyExistsException("Serial number already exists");
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
                        new ResourceNotFoundException("Asset not found"));

        asset.setStatus(AssetStatus.RETIRED);

        assetRepository.save(asset);
    }
}
