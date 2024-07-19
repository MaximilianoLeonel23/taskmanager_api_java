package com.taskmanager.api.dto.task;

import com.taskmanager.api.model.Status;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long userId
) {
}
