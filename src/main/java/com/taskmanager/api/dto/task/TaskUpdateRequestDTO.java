package com.taskmanager.api.dto.task;

import com.taskmanager.api.model.Status;

public record TaskUpdateRequestDTO(
        String title,
        String description,
        Status status
) {
}
