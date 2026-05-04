package com.davidgorraiz.userapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record UserDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
    String username,
    String email,
    String password,
    Boolean enabled,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime lastLogin
) {
}
