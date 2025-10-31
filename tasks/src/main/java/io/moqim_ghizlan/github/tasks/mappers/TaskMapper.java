package io.moqim_ghizlan.github.tasks.mappers;

import io.moqim_ghizlan.github.tasks.domain.dto.TaskDto;
import io.moqim_ghizlan.github.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
