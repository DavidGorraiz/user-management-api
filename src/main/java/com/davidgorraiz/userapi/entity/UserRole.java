package com.davidgorraiz.userapi.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;
    @Column(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Long roleId;
    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;
    @Column(name = "assigned_by", nullable = false)
    private Long assignedBy;
    @Column(name = "revoked_at")
    private LocalDateTime revokedAt;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserRole(Long id, Long userId, Long roleId, LocalDateTime assignedAt,
                    Long assignedBy, LocalDateTime revokedAt) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.assignedAt = assignedAt;
        this.assignedBy = assignedBy;
        this.revokedAt = revokedAt;
    }

    public UserRole() {
    }
}
