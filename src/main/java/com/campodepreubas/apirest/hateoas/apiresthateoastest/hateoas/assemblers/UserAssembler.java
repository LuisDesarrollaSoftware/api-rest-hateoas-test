package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.controller.UserController;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<User, UserResource> {

    @Autowired
    private PlanOfTaskAssembler planOfTaskAssembler;

    public UserAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toModel(User user) {
        UserResource resource = instantiateModel(user);
        resource.setId(user.getId());
        resource.setName(user.getName());
        resource.setEmail(user.getEmail());
        if (user.getPlanOftask() != null) {
            resource.setPlanOftask(planOfTaskAssembler.toModel(user.getPlanOftask()));
        }
        resource.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
        return resource;
    }
}
