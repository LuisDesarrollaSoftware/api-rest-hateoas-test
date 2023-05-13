package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class TaskResponse {

    private Long id;
    private String name;
    private double effort;
    private List<PlanOfTaskResponse> planOfTask;
    private PeriodTaskResponse period;
}
