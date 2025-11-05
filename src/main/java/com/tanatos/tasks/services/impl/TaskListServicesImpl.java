package com.tanatos.tasks.services.impl;

import com.tanatos.tasks.domain.entities.TaskList;
import com.tanatos.tasks.repositories.TaskListRepositories;
import com.tanatos.tasks.services.TaskListServices;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskListServicesImpl implements TaskListServices {

    private final TaskListRepositories taskListRepositories;

    public TaskListServicesImpl(TaskListRepositories taskListRepositories) {
        this.taskListRepositories = taskListRepositories;
    }


    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepositories.findAll();
    }
}
