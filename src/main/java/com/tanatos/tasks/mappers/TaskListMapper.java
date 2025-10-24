package com.tanatos.tasks.mappers;

import com.tanatos.tasks.domain.dto.TaskListDto;
import com.tanatos.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
