package com.taskmanager.api.dto;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
