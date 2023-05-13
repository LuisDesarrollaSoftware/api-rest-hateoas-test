package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

public @Data class UserResource  extends RepresentationModel<UserResource> {

    private Long id;
    private String name;
    private String email;
    private PlanOfTaskResource planOftask;
}
