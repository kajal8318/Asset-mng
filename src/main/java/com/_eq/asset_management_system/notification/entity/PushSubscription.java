
package com._eq.asset_management_system.notification.entity;
import com._eq.asset_management_system.common.entity.BaseEntity;
import com._eq.asset_management_system.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "push_subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_push_subscription_endpoint",
                        columnNames = "endpoint"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PushSubscription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_push_subscription_user")
    )
    private User user;

    @Column(name = "endpoint", nullable = false, columnDefinition = "TEXT")
    private String endpoint;

    @Column(name = "p256dh", nullable = false, columnDefinition = "TEXT")
    private String p256dh;

    @Column(name = "auth", nullable = false, columnDefinition = "TEXT")
    private String auth;
}