package io.moqim_ghizlan.github.tasks.mappers.impl;

import io.moqim_ghizlan.github.tasks.domain.dto.TaskDto;
import io.moqim_ghizlan.github.tasks.domain.dto.TaskListDto;
import io.moqim_ghizlan.github.tasks.domain.entities.Task;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskList;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskStatus;
import io.moqim_ghizlan.github.tasks.mappers.TaskListMapper;
import io.moqim_ghizlan.github.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Component
public class TaskKistMapperimpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskKistMapperimpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                mapTasksFromDto(taskListDto.tasks()),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                getTaskCount(taskList.getTasks()),
                calculateTaskListProgress(taskList.getTasks()),
                mapTasksToDto(taskList.getTasks())

        );
    }
    private int getTaskCount(List<Task> tasks) {
        return tasks == null ? 0 : tasks.size();
    }

    private List<TaskDto> mapTasksToDto(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    private List<Task> mapTasksFromDto(List<TaskDto> taskDtos) {
        if (taskDtos == null) {
            return null;
        }
        return taskDtos.stream()
                .map(taskMapper::fromDto)
                .toList();
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks || tasks.isEmpty()) {
            return (double) 0;
        }
        long closedTaskCount = tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();
    return (double) closedTaskCount / tasks.size();
    }
}
