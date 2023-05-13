package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public  class UserResponse {

    private Long id;
    private String name;
    private String email;
    private PlanOfTaskResponse planOftask;
}
