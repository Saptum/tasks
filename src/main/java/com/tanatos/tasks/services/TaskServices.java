package com.tanatos.tasks.services;

import com.tanatos.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskServices {
    List<Task> listTasks(UUID taskListId);

}
