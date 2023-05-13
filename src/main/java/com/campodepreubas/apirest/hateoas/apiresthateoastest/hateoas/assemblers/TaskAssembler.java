package com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.controller.TaskController;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TaskAssembler extends RepresentationModelAssemblerSupport<Task, TaskResource> {

    @Autowired
    private PeriodTaskAssembler periodTaskAssembler;

    public TaskAssembler() {
        super(TaskController.class, TaskResource.class);
    }

    @Override
    public TaskResource toModel(Task task) {
        TaskResource resource = instantiateModel(task);
        resource.setName(task.getName());
        resource.setEffort(task.getEffort());
        if(Objects.nonNull(task.getPeriod())) {
            resource.setPeriod(periodTaskAssembler.toModel(task.getPeriod()));
        }
        resource.add(linkTo(methodOn(TaskController.class).getTask(task.getId())).withSelfRel());

        return resource;
    }
}
