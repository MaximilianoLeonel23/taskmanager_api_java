package com.taskmanager.api.dto.task;

public record TaskRequestDTO(
        String title,
        String description
) {
}
