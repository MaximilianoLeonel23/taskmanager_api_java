package com.taskmanager.api.dto.auth;

import java.time.LocalDateTime;

public record UserSignUpResponseDTO(
        Long id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
