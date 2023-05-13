package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

public @Data class TaskResource extends RepresentationModel<TaskResource> {

    private String name;
    private double effort;
    private PeriodTaskResource period;
}
