package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.PeriodicityEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public final class PeriodTaskRequest {

    private String name;
    private PeriodicityEnum periodicity;
}
