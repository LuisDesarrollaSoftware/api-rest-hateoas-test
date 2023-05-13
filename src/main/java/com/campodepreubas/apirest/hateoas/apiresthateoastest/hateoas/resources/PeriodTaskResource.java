package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.PeriodicityEnum;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

public @Data class PeriodTaskResource extends RepresentationModel<PlanOfTaskResource> {

    private Long id;
    private String name;
    private PeriodicityEnum periodicity;
}
