package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class TaskRequest {

    private String name;
    private double effort;
}
