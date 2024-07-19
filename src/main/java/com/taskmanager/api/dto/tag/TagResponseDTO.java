package com.taskmanager.api.dto.tag;

public record TagResponseDTO(
        Long id,
        String name,
        Long userId
) {
}
