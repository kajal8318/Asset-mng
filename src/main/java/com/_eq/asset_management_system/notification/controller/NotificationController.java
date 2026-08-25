package com._eq.asset_management_system.notification.controller;

import java.util.List;

import com._eq.asset_management_system.notification.dto.NotificationCreateDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com._eq.asset_management_system.notification.dto.NotificationResponseDto;
import com._eq.asset_management_system.notification.service.NotificationService;
import com._eq.asset_management_system.notification.service.sse.SseEmitterService;
import com._eq.asset_management_system.user.entity.User;
import com._eq.asset_management_system.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final SseEmitterService sseEmitterService;

    private final UserService userService;

    @GetMapping(
            value = "/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter subscribe(Authentication authentication) {

        String firebaseUid = authentication.getName();

        User user =
                userService.getUserByFirebaseUid(firebaseUid);

        return sseEmitterService.subscribe(user.getId());
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>>
    getMyNotifications() {

        return ResponseEntity.ok(
                notificationService.getMyNotifications()
        );
    }

    @GetMapping("/unread")
    public ResponseEntity<List<NotificationResponseDto>>
    getUnreadNotifications() {

        return ResponseEntity.ok(
                notificationService.getUnreadNotifications()
        );
    }

    @PostMapping("/broadcast")
    public ResponseEntity<Void> broadcastNotification(
            @RequestBody NotificationCreateDto request) {

        notificationService.broadcastNotification(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/unread/count")
    public ResponseEntity<Long> getUnreadCount() {

        return ResponseEntity.ok(
                notificationService.getUnreadCount()
        );
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long notificationId
    ) {

        notificationService.markAsRead(notificationId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead() {

        notificationService.markAllAsRead();

        return ResponseEntity.noContent().build();
    }
}