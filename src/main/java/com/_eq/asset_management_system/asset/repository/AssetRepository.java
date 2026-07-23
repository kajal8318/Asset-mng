package com._eq.asset_management_system.asset.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.common.enums.AssetStatus;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    boolean existsByAssetCode(String assetCode);

    boolean existsBySerialNumber(String serialNumber);

    List<Asset> findByStatusNot(AssetStatus status);

    Optional<Asset> findByIdAndStatusNot(Long id, AssetStatus status);

}