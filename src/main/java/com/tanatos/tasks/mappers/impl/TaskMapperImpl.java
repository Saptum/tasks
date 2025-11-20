package com.tanatos.tasks.mappers.impl;

import com.tanatos.tasks.domain.dto.TaskDto;
import com.tanatos.tasks.domain.entities.Task;
import com.tanatos.tasks.domain.entities.TaskPriority;
import com.tanatos.tasks.domain.entities.TaskStatus;
import com.tanatos.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        if (taskDto.title() == null || taskDto.title().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        Task task = new Task();
        task.setId(taskDto.id());
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setDueDate(taskDto.dueDate());
        task.setPriority(taskDto.priority() != null ? taskDto.priority() : TaskPriority.MEDIUM);
        task.setStatus(taskDto.status() != null ? taskDto.status() : TaskStatus.OPEN);
        return task;
//        return new Task(
//                taskDto.id(),
//                taskDto.title(),
//                taskDto.description(),
//                taskDto.dueDate(),
//                taskDto.status(),
//                taskDto.priority(),
//                null,
//                null,
//                null
//        );
    }

    @Override
    public TaskDto toDto(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null in toDto method");
        }
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
