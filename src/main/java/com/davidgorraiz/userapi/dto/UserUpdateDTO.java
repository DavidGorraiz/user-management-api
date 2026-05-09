package com.davidgorraiz.userapi.dto;

import java.time.LocalDateTime;

public record UserUpdateDTO(
        String username,
        String email,
        String password,
        Boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime lastLogin
) {
}
