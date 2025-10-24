package com.tanatos.tasks.mappers;

import com.tanatos.tasks.domain.dto.TaskDto;
import com.tanatos.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
