package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.PeriodicityEnum;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;

public @Data class PeriodTaskResource extends RepresentationModel<PlanOfTaskResource> {

    private String name;
    private Integer periodicity;
}
