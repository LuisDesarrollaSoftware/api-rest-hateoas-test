package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.PlanOfTaskAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PlanOfTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.TaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PlanOfTaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.TaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.PlanOfTaskRepository;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlanOfTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PlanOfTaskRepository planOfTaskRepository;

    @Autowired
    private PlanOfTaskAssembler planOfTaskResourceAssembler;

    public List<PlanOfTaskResource> findAll() {
        return planOfTaskRepository.findAll().stream().map(planOfTaskResourceAssembler::toModel).toList();
    }

    public PlanOfTaskResource findById(Long id) {
        return planOfTaskRepository.findById(id)
                .map(planOfTaskResourceAssembler::toModel)
                .orElseThrow(()-> new RuntimeException("PlanOfTask not found"));
    }

    public PlanOfTaskResource save(PlanOfTaskRequest planOfUser) {

        PlanOfTask planOfTask= PlanOfTask.builder()
                .name(planOfUser.getName())
                .description(planOfUser.getDescription())
                .build();

        PlanOfTask saved = planOfTaskRepository.save(planOfTask);
        PlanOfTaskResource resource = planOfTaskResourceAssembler.toModel(saved);

        return resource;
    }

    public PlanOfTaskResource update(Long id, PlanOfTaskRequest planOfUserRequest){

        if (Objects.isNull(planOfUserRequest)) {
            throw new IllegalArgumentException("PlanOfTaskRequest is null");
        }

        PlanOfTask planOfTaskPrevius = planOfTaskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("PlanOfTask not found")
        );

        PlanOfTask planOfTask = PlanOfTask.builder()
                .id(id)
                .name(planOfUserRequest.getName())
                .description(planOfUserRequest.getDescription())
                .startDate(planOfTaskPrevius.getStartDate())
                .task(planOfTaskPrevius.getTask())
                .build();

        PlanOfTask saved = planOfTaskRepository.save(planOfTask);

        PlanOfTaskResource resource = planOfTaskResourceAssembler.toModel(saved);

        return resource;
    }

    public Boolean delete(Long id) {
        return planOfTaskRepository.findById(id)
                .map(record -> {
                    planOfTaskRepository.deleteById(id);
                    planOfTaskResourceAssembler.toModel(record);
                    return true;
                }).orElse(false);
    }


    public PlanOfTaskResource addTaskInPlan(Long id, Long idTask) {
        TaskResource taskResource = taskService.findById(idTask);
        PeriodTask periodTask = PeriodTask.builder()
                .periodicity(taskResource.getPeriod().getPeriodicity())
                .name(taskResource.getPeriod().getName())
                .build();

        Task task = Task.builder()
                .id(idTask)
                .name(taskResource.getName())
                .effort(taskResource.getEffort())
                .period(periodTask)
                .build();

        PlanOfTask planOfTask = planOfTaskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("PlanOfTask not found")
        );

        if(!planOfTask.getTask().contains(task)) {

            planOfTask.getTask().add(task);
            PlanOfTask updated = planOfTaskRepository.save(planOfTask);
            return planOfTaskResourceAssembler.toModel(updated);
        }
        return planOfTaskResourceAssembler.toModel(planOfTask);
    }

    public Boolean removeTaskInPlan(Long id, Long idTask) {


        return planOfTaskRepository.findById(id)
                .map(record -> {

                    Task Task = record.getTask().stream()
                            .filter(task -> task.getId().equals(idTask))
                            .toList().get(0);

                    record.getTask().remove(Task);

                    PlanOfTask updated = planOfTaskRepository.save(record);

                   ResponseEntity.ok().body(planOfTaskResourceAssembler.toModel(updated));
                   return true;
                }).orElse(false);
    }

    public PlanOfTaskResource patchTaskInPlan(Long id, Long idTask) {
            PlanOfTask planOfTask =  planOfTaskRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("PlanOfTask not found")
            );
        TaskResource taskResource = taskService.findById(idTask);
        PeriodTask periodTask = PeriodTask.builder()
                .periodicity(taskResource.getPeriod().getPeriodicity())
                .name(taskResource.getPeriod().getName())
                .build();

        Task task = Task.builder()
                .id(idTask)
                .name(taskResource.getName())
                .effort(taskResource.getEffort())
                .period(periodTask)
                .build();
        planOfTask.getTask().add(task);

        PlanOfTask updated = planOfTaskRepository.saveAndFlush(planOfTask);
        return planOfTaskResourceAssembler.toModel(updated);
    }
}
