package com._eq.asset_management_system.notification.service.sse;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseEmitterService {

    SseEmitter subscribe(Long userId);

    void sendNotification(Long userId, Object notification);

    void broadcastNotification(Object notification);

    void removeEmitter(Long userId);
}