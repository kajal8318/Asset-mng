package com._eq.asset_management_system.notification.service.push.impl;

import com._eq.asset_management_system.notification.dto.PushSubscriptionRequestDto;
import com._eq.asset_management_system.notification.entity.PushSubscription;
import com._eq.asset_management_system.notification.repository.PushSubscriptionRepository;
import com._eq.asset_management_system.notification.service.push.PushNotificationService;
import com._eq.asset_management_system.user.entity.User;
import com._eq.asset_management_system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PushNotificationServiceImpl
        implements PushNotificationService {

    private final PushSubscriptionRepository pushSubscriptionRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void subscribe(
            User user,
            PushSubscriptionRequestDto request) {

        PushSubscription subscription =
                pushSubscriptionRepository
                        .findByEndpoint(request.getEndpoint())
                        .orElse(new PushSubscription());

        subscription.setUser(user);
        subscription.setEndpoint(request.getEndpoint());
        subscription.setP256dh(request.getKeys().getP256dh());
        subscription.setAuth(request.getKeys().getAuth());

        pushSubscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void unsubscribe(
            User user,
            String endpoint) {

        pushSubscriptionRepository
                .findByEndpoint(endpoint)
                .ifPresent(subscription -> {

                    if (subscription.getUser().getId().equals(user.getId())) {
                        pushSubscriptionRepository.delete(subscription);
                    }
                });
    }
}
