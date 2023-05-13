package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.controller.PeriodTaskController;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PeriodTaskResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PeriodTaskAssembler extends RepresentationModelAssemblerSupport<PeriodTask, PeriodTaskResource> {


    public PeriodTaskAssembler() {
        super(PeriodTaskController.class, PeriodTaskResource.class);
    }


    @Override
    public PeriodTaskResource toModel(PeriodTask entity) {
        PeriodTaskResource resource = instantiateModel(entity);
        resource.setId(entity.getId());
        resource.setName(entity.getName());
        resource.setPeriodicity(entity.getPeriodicity());;

        return resource;
    }
}
