package com._eq.asset_management_system.notification.repository;


import com._eq.asset_management_system.notification.entity.PushSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PushSubscriptionRepository
        extends JpaRepository<PushSubscription, Long> {

    Optional<PushSubscription> findByEndpoint(String endpoint);

    List<PushSubscription> findAllByUserId(Long userId);

    void deleteByEndpoint(String endpoint);
}
