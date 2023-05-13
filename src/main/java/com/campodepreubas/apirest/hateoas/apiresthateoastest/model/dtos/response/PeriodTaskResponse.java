package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.PeriodicityEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PeriodTaskResponse {

    private Long id;
    private String name;
    private List<Task> tasks;
    private PeriodicityEnum periodicity;
}
