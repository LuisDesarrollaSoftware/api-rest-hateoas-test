package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.controller.PlanOfTaskController;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.controller.UserController;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PlanOfTaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlanOfTaskAssembler extends RepresentationModelAssemblerSupport<PlanOfTask, PlanOfTaskResource> {

    @Autowired
    private TaskAssembler taskAssembler;


    public PlanOfTaskAssembler() {
        super(PlanOfTaskController.class, PlanOfTaskResource.class);
    }

    @Override
    public PlanOfTaskResource toModel(PlanOfTask entity) {
        PlanOfTaskResource resource = instantiateModel(entity);
        resource.setId(entity.getId());
        resource.setName(entity.getName());
        resource.setDescription(entity.getDescription());
        resource.setStatus(entity.getStatus());
        if(Objects.nonNull(entity.getTask())) {
            resource.setTask(entity.getTask().stream().map(taskAssembler::toModel).filter(Objects::nonNull).toList());
        }
        resource.setStartDate(entity.getStartDate());
        resource.add(linkTo(methodOn(PlanOfTaskController.class).getPlanOfUser(entity.getId())).withRel("planOfUser"));
        if(Objects.nonNull(entity.getUser())){
            resource.add(linkTo(methodOn(UserController.class).getUser(entity.getUser().getId())).withRel("user"));
        }
        return resource;
    }
}
