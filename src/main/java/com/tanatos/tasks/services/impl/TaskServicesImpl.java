package com.tanatos.tasks.services.impl;

import com.tanatos.tasks.domain.entities.Task;
import com.tanatos.tasks.repositories.TaskRepositories;
import com.tanatos.tasks.services.TaskServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServicesImpl implements TaskServices {


    private final TaskRepositories  taskRepositories;

    public TaskServicesImpl(TaskRepositories taskRepositories) {
        this.taskRepositories = taskRepositories;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepositories.findByTaskListId(taskListId);
    }


}
