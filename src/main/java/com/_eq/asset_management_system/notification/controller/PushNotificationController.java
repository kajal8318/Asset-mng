package com._eq.asset_management_system.notification.controller;
import com._eq.asset_management_system.notification.dto.PushSubscriptionRequestDto;
import com._eq.asset_management_system.notification.service.push.PushNotificationService;
import com._eq.asset_management_system.user.entity.User;
import com._eq.asset_management_system.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/notifications/push")
@RequiredArgsConstructor
public class PushNotificationController {

    private final PushNotificationService pushNotificationService;
    private final UserService userService;

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(
            @AuthenticationPrincipal String firebaseUid,
            @Valid @RequestBody PushSubscriptionRequestDto request) {

        User user = userService.getUserByFirebaseUid(firebaseUid);

        pushNotificationService.subscribe(user, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(
            @AuthenticationPrincipal String firebaseUid,
            @RequestParam String endpoint) {

        User user = userService.getUserByFirebaseUid(firebaseUid);

        pushNotificationService.unsubscribe(user, endpoint);

        return ResponseEntity.noContent().build();
    }
}
