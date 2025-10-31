package io.moqim_ghizlan.github.tasks.services.impl;

import io.moqim_ghizlan.github.tasks.domain.entities.Task;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskList;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskPriority;
import io.moqim_ghizlan.github.tasks.domain.entities.TaskStatus;
import io.moqim_ghizlan.github.tasks.repositories.TaskListRepository;
import io.moqim_ghizlan.github.tasks.repositories.TaskRepository;
import io.moqim_ghizlan.github.tasks.services.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()) {
            throw new IllegalArgumentException("Task already exists!");
        }
        if(null == task.getTitle() || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Task title is empty!");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList= taskListRepository.findById(taskListId).
                orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided"));
        LocalDateTime now = LocalDateTime.now();
        Task taskToSave  = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueTate(),
                taskStatus,
                taskPriority,
                now,
                now,
                taskList
        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }
    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()) {
            throw new IllegalArgumentException("Task id is empty!");
        }
        if(!Objects.equals(task.getId(), taskId)) {
            throw new IllegalArgumentException("Task Ids do not match!");
        }

        if(null == task.getTitle() || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Task title is empty!");
        }

        if(null == task.getPriority()) {
            throw new IllegalArgumentException("Task priority is empty!");
        }
        if(null == task.getStatus()) {
            throw new IllegalArgumentException("Task status is empty!");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID provided"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueTate(task.getDueTate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setTaskList(task.getTaskList());
        return taskRepository.save(existingTask);



    }


    @Transactional
    @Override
    public void daleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }


}
