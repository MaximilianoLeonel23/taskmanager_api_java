package com.taskmanager.api.dto.tag;

public record TagRequestDTO(
        String name,
        Long userId
) {
}
