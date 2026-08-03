package com._eq.asset_management_system.asset.specification;

import com._eq.asset_management_system.asset.entity.Asset;
import com._eq.asset_management_system.common.enums.AssetStatus;
import org.springframework.data.jpa.domain.Specification;

public class AssetSpecification {
    public static Specification<Asset> hasStatus(AssetStatus status) {

        return (root, query, cb) -> {

            if (status == null) {
                return cb.conjunction();
            }

            return cb.equal(root.get("status"), status);
        };
    }
    public static Specification<Asset> hasCategory(String category) {

        return (root, query, cb) -> {

            if (category == null || category.isBlank()) {
                return cb.conjunction();
            }

            return cb.equal(
                    cb.lower(root.get("category")),
                    category.toLowerCase()
            );
        };
    }
    public static Specification<Asset> hasAssetCode(String assetCode) {

        return (root, query, cb) -> {

            if (assetCode == null || assetCode.isBlank()) {
                return cb.conjunction();
            }

            return cb.equal(root.get("assetCode"), assetCode);
        };
    }
    public static Specification<Asset> hasSerialNumber(String serialNumber) {

        return (root, query, cb) -> {

            if (serialNumber == null || serialNumber.isBlank()) {
                return cb.conjunction();
            }

            return cb.equal(root.get("serialNumber"), serialNumber);
        };
    }
    public static Specification<Asset> assetNameContains(String assetName) {

        return (root, query, cb) -> {

            if (assetName == null || assetName.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(
                    cb.lower(root.get("assetName")),
                    "%" + assetName.toLowerCase() + "%"
            );
        };
    }
}
