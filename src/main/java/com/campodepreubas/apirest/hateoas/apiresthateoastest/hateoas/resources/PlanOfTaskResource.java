package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.StatusTaskEnum;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;
import java.util.List;

public @Data class PlanOfTaskResource extends RepresentationModel<PlanOfTaskResource> {

    private Long id;
    private String name;
    private String description;
    private StatusTaskEnum status;
    private List<TaskResource> task;
    private Timestamp startDate;
}
