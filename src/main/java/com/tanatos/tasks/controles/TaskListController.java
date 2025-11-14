package com.tanatos.tasks.controles;


import com.tanatos.tasks.domain.dto.TaskListDto;
import com.tanatos.tasks.domain.entities.TaskList;
import com.tanatos.tasks.mappers.TaskListMapper;
import com.tanatos.tasks.services.TaskListServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id")UUID taskListId){
        return  taskListService.getTaskList( taskListId).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto,
            TaskListMapper taskListMapper
    ){
          TaskList updateTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );

          return  taskListMapper.toDto(updateTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id")UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }



}
