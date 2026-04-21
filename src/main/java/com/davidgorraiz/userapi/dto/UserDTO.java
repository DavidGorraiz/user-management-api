package com.davidgorraiz.userapi.dto;

import java.time.LocalDateTime;

public record UserDTO(
    Long id,
    String username,
    String email,
    Boolean enabled,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime lastLogin
) {
}
