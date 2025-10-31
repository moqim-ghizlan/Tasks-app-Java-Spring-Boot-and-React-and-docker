package io.moqim_ghizlan.github.tasks.mappers.impl;

import io.moqim_ghizlan.github.tasks.domain.dto.TaskDto;
import io.moqim_ghizlan.github.tasks.domain.entities.Task;
import io.moqim_ghizlan.github.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
               taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.duoDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null

        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueTate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
