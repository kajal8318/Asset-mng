package com._eq.asset_management_system.asset.controller;

import java.util.List;

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
    public AssetResponseDto createAsset(@RequestBody AssetRequestDto request) {
        return assetService.createAsset(request);
    }

    @GetMapping
    public List<AssetResponseDto> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public AssetResponseDto getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }

    @PutMapping("/{id}")
    public AssetResponseDto updateAsset(@PathVariable Long id,
                                        @RequestBody AssetRequestDto request) {
        return assetService.updateAsset(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }
}