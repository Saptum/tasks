package com.tanatos.tasks.controles;


import com.tanatos.tasks.domain.dto.TaskDto;
import com.tanatos.tasks.domain.entities.Task;
import com.tanatos.tasks.mappers.TaskMapper;
import com.tanatos.tasks.services.TaskServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping
    public TaskDto createTask(
            @PathVariable("task_list_id")UUID taskListId,
            @RequestBody TaskDto taskDto){
        Task createdTask =taskServices.createTask(
                taskListId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(createdTask);
    }

    @GetMapping(path = "/{task_id}")
    public Optional<TaskDto> getTask(
        @PathVariable("task_list_id")UUID taskListId,
        @PathVariable("task_id")UUID taskId
    ){
        return taskServices.getTask(taskListId, taskId)
                .map(taskMapper::toDto);
    }

    @PostMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("task_id")UUID taskId,
            @RequestBody TaskDto taskDto
    ){
        Task updatedTask = taskServices.updateTask(
                taskListId,
                taskId,
                taskMapper.fromDto(taskDto)
        );
        return taskMapper.toDto(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id")UUID taskListId,
            @PathVariable("task_id")UUID taskId
    ){
        taskServices.deleteTask(taskListId, taskId);
    }

}
