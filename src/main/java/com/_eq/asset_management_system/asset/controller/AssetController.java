package com._eq.asset_management_system.asset.controller;

import java.util.List;

import com._eq.asset_management_system.asset.dto.AssetSearchRequest;
import com._eq.asset_management_system.common.dto.PageResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com._eq.asset_management_system.asset.dto.AssetRequestDto;
import com._eq.asset_management_system.asset.dto.AssetResponseDto;
import com._eq.asset_management_system.asset.service.AssetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public AssetResponseDto createAsset( @Valid @RequestBody AssetRequestDto request) {
        return assetService.createAsset(request);
    }

    @GetMapping
    public PageResponse<AssetResponseDto> getAllAssets(
            @ModelAttribute AssetSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "assetCode") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return assetService.getAllAssets(
                request,
                page,
                size,
                direction,
                sortBy);
    }

    @GetMapping("/{id}")
    public AssetResponseDto getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }

    @PutMapping("/{id}")
    public AssetResponseDto updateAsset( @Valid @PathVariable Long id,
                                        @RequestBody AssetRequestDto request) {
        return assetService.updateAsset(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }
}