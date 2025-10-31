package io.moqim_ghizlan.github.tasks.services.impl;

import io.moqim_ghizlan.github.tasks.domain.entities.TaskList;
import io.moqim_ghizlan.github.tasks.repositories.TaskListRepository;
import io.moqim_ghizlan.github.tasks.services.TaskListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }
    @Transactional
    @Override
    public TaskList createTaskLists(TaskList taskList) {
        if(null != taskList.getId()){
            throw new IllegalArgumentException("Tha tasklist already has an ID");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isEmpty()){
            throw new IllegalArgumentException("Tha tasklist must have a title");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()){
            throw new IllegalArgumentException("Tha tasklist must have an ID");
        }
        if(!Objects.equals(taskList.getId(), taskListId)){
            throw new IllegalArgumentException("Attempting to change task list OD, this is not allowed");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(
                ()->new IllegalArgumentException("Tasklist with id "+taskListId+" does not exist"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedAt(taskList.getUpdatedAt());
        return taskListRepository.save(existingTaskList);
    }
    @Transactional
    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }


}
