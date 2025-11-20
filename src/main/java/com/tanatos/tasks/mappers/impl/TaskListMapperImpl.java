package com.tanatos.tasks.mappers.impl;

import com.tanatos.tasks.domain.dto.TaskListDto;
import com.tanatos.tasks.domain.entities.Task;
import com.tanatos.tasks.domain.entities.TaskList;
import com.tanatos.tasks.domain.entities.TaskStatus;
import com.tanatos.tasks.mappers.TaskListMapper;
import com.tanatos.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {


    private  final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
//        return new TaskList(
//                taskListDto.id(),
//                taskListDto.title(),
//                taskListDto.description(),
//                Optional.ofNullable(taskListDto.tasks())
//                        .map(tasks -> tasks.stream()
//                                .map(taskMapper::fromDto)
//                                .toList()
//                        ).orElse(null),
//                null,
//                null
//        );
        TaskList taskList = new TaskList();
        taskList.setId(taskListDto.id());
        taskList.setTitle(taskListDto.title());
        taskList.setDescription(taskListDto.description());

        if (taskListDto.tasks() != null) {
            List<Task> tasks = taskListDto.tasks().stream()
                    .map(taskMapper::fromDto)
                    .toList();
            taskList.setTasks(tasks);
        }

        LocalDateTime now = LocalDateTime.now();
        taskList.setCreated(now);
        taskList.setUpdated(now);

        return taskList;
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {

        if (taskList == null) {
            throw new IllegalArgumentException("TaskList cannot be null in toDto method");
        }

        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks ->
                                tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(null == tasks || tasks.isEmpty()){
            return  0.0;
        }

        long closedTaskCount = tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();

        return (double)closedTaskCount / tasks.size();
    }
}
