package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class UserRequest {
    private String name;
    private String email;
}
