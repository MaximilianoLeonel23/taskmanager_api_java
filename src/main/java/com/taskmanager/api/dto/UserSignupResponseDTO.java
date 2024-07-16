package com.taskmanager.api.dto;

public record UserSignupResponseDTO(
        Long id,
        String username,
        String email
) {
}
