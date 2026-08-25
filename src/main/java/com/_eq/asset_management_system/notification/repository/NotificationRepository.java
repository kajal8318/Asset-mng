package com._eq.asset_management_system.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com._eq.asset_management_system.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<Notification> findByUserIdAndReadFalseOrderByCreatedAtDesc(Long userId);

    long countByUserIdAndReadFalse(Long userId);
}