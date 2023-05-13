package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.StatusTaskEnum;
import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public final class PlanOfTaskRequest {

    private String name;
    private String description;
    private StatusTaskEnum status;
    private LocalDate startDate;
}
