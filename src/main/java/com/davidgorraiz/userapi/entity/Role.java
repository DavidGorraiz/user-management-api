package com.davidgorraiz.userapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }
}
