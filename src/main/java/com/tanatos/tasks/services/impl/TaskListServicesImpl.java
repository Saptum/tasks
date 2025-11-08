package com.tanatos.tasks.services.impl;

import com.tanatos.tasks.domain.entities.TaskList;
import com.tanatos.tasks.repositories.TaskListRepositories;
import com.tanatos.tasks.services.TaskListServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null != taskList.getId()){
            throw new IllegalArgumentException("Task list already has an ID");
        }
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title must be present");
        }

        LocalDateTime now = LocalDateTime.now();
        taskListRepositories.save( new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));


        return null;
    }
}
