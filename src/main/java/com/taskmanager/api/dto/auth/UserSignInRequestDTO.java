package com.taskmanager.api.dto.auth;

public record UserSignInRequestDTO(
        String username,
        String password
) {
}
