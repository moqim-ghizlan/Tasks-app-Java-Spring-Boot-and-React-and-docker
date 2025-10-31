package io.moqim_ghizlan.github.tasks.domain.dto;

import io.moqim_ghizlan.github.tasks.domain.entities.TaskPriority;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime duoDate,
        TaskPriority priority,
        TaskStatus status
) {

}
