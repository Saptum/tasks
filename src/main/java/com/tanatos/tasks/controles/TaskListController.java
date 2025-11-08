package com.tanatos.tasks.controles;


import com.tanatos.tasks.domain.dto.TaskListDto;
import com.tanatos.tasks.mappers.TaskListMapper;
import com.tanatos.tasks.services.TaskListServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( path = "/task-lists")
public class TaskListController {


    private  final TaskListServices taskListService;
    private  final TaskListMapper taskListMapper;

    public TaskListController(TaskListServices taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> taskListList(){
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();

    }
}
