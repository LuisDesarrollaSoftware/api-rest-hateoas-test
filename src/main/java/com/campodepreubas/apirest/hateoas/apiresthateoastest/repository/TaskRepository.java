package com.campodepreubas.apirest.hateoas.apiresthateoastest.repository;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

