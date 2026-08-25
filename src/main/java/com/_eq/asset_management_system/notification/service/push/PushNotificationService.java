package com._eq.asset_management_system.notification.service.push;


import com._eq.asset_management_system.notification.dto.PushSubscriptionRequestDto;
import com._eq.asset_management_system.user.entity.User;


public interface PushNotificationService {

        void subscribe(User user, PushSubscriptionRequestDto request);

        void unsubscribe(User user, String endpoint);

}
