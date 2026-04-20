package com.davidgorraiz.userapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    public User(Long id, String username, String email, String password,
                boolean enabled, LocalDateTime createdAt, LocalDateTime updatedAt,
                LocalDateTime lastLogin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
    }

    public User() {
    }
}
