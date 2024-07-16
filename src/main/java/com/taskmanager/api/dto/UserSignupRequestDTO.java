package com.taskmanager.api.dto;

public record UserSignupRequestDTO(
        String username,
        String email,
        String password
) {
}
