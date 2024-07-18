package com.taskmanager.api.dto.auth;

public record UserSignUpRequestDTO(
        String username,
        String password,
        String email
) {
}
