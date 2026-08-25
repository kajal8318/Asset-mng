package com._eq.asset_management_system.notification.service.sse.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com._eq.asset_management_system.notification.service.sse.SseEmitterService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseEmitterServiceImpl implements SseEmitterService {

    private static final long TIMEOUT = 30 * 60 * 1000L;

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Override
    public SseEmitter subscribe(Long userId) {

        SseEmitter emitter = new SseEmitter(TIMEOUT);

        emitters.put(userId, emitter);

        emitter.onCompletion(() -> {
            emitters.remove(userId);
        });

        emitter.onTimeout(() -> {
            emitters.remove(userId);
        });

        emitter.onError((error) -> {
            emitters.remove(userId);
        });

        try {
            emitter.send(
                SseEmitter.event()
                    .name("connected")
                    .data("SSE connection established")
            );
        } catch (IOException e) {
            emitters.remove(userId);
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @Override
    public void broadcastNotification(Object notification) {

        emitters.forEach((userId, emitter) -> {

            try {

                emitter.send(
                        SseEmitter.event()
                                .name("notification")
                                .data(notification)
                );

            } catch (IOException e) {

                emitters.remove(userId);
                emitter.completeWithError(e);
            }
        });
    }
    @Override
    public void sendNotification(Long userId, Object notification) {

        SseEmitter emitter = emitters.get(userId);

        if (emitter == null) {
            return;
        }

        try {

            emitter.send(
                SseEmitter.event()
                    .name("notification")
                    .data(notification)
            );

        } catch (IOException e) {

            emitters.remove(userId);
            emitter.completeWithError(e);
        }
    }

    @Override
    public void removeEmitter(Long userId) {

        SseEmitter emitter = emitters.remove(userId);

        if (emitter != null) {
            emitter.complete();
        }
    }
}