package com.davidgorraiz.userapi.dto;

import com.davidgorraiz.userapi.entity.Role;
import com.davidgorraiz.userapi.entity.User;

import java.time.LocalDateTime;

public record UserRoleDTO(
        Long id,
        User user,
        Role role,
        LocalDateTime assignedAt,
        Long assignedBy,
        LocalDateTime revokedAt
) {
}
