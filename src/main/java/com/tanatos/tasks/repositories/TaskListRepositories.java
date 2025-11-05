package com.tanatos.tasks.repositories;

import com.tanatos.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepositories extends JpaRepository<TaskList, UUID> {

}
