package com.campodepreubas.apirest.hateoas.apiresthateoastest.repository;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanOfTaskRepository extends JpaRepository<PlanOfTask, Long> {
}

