package com.campodepreubas.apirest.hateoas.apiresthateoastest.repository;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<PeriodTask, Integer> {
}

