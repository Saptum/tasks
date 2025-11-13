package com.tanatos.tasks.controles;


import com.tanatos.tasks.domain.dto.TaskDto;
import com.tanatos.tasks.mappers.TaskMapper;
import com.tanatos.tasks.services.TaskServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskServices taskServices;
    private final TaskMapper taskMapper;

    public TasksController(TaskServices taskServices, TaskMapper taskMapper) {
        this.taskServices = taskServices;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id")UUID taskListId){
        return taskServices.listTasks(taskListId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
}
