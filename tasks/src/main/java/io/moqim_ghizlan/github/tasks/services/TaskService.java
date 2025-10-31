package io.moqim_ghizlan.github.tasks.services;

import io.moqim_ghizlan.github.tasks.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
    Task updateTask(UUID taskListId, UUID taskId, Task task);
    void daleteTask(UUID taskListId, UUID taskId);
}
