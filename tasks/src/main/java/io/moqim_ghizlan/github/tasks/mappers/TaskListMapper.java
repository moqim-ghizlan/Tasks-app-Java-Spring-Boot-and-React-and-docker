package io.moqim_ghizlan.github.tasks.mappers;

import io.moqim_ghizlan.github.tasks.domain.dto.TaskDto;
import io.moqim_ghizlan.github.tasks.domain.dto.TaskListDto;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
