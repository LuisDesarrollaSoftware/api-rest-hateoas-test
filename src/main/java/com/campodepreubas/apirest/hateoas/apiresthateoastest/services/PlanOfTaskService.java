package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.PlanOfTaskAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PlanOfTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PlanOfTaskRequest;
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
    private TaskRepository taskRepository;

    @Autowired
    private PlanOfTaskRepository planOfTaskRepository;

    @Autowired
    private PlanOfTaskAssembler planOfTaskResourceAssembler;

    public ResponseEntity<List<PlanOfTaskResource>> findAll() {
        return ResponseEntity.ok(planOfTaskRepository.findAll().stream().map(planOfTaskResourceAssembler::toModel).toList());
    }

    public ResponseEntity<PlanOfTaskResource> findById(Long id) {
        return planOfTaskRepository.findById(id)
                .map(planOfTaskResourceAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<PlanOfTaskResource> save(PlanOfTaskRequest planOfUser) {

        PlanOfTask planOfTask= PlanOfTask.builder()
                .name(planOfUser.getName())
                .description(planOfUser.getDescription())
                .build();

        PlanOfTask saved = planOfTaskRepository.save(planOfTask);
        PlanOfTaskResource resource = planOfTaskResourceAssembler.toModel(saved);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);
    }

    public ResponseEntity<PlanOfTaskResource> update(Long id, PlanOfTaskRequest planOfUserRequest){

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

        return ResponseEntity.accepted().body(resource);
    }

    public ResponseEntity<PlanOfTaskResource> delete(Long id) {
        return planOfTaskRepository.findById(id)
                .map(record -> {
                    planOfTaskRepository.deleteById(id);
                    return ResponseEntity.ok().body(planOfTaskResourceAssembler.toModel(record));
                }).orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<PlanOfTaskResource> addTaskInPlan(Long id, Long idTask) {
        Task task = taskRepository.findById(idTask).orElseThrow(
                () -> new IllegalArgumentException("Task not found")
        );

        PlanOfTask planOfTask = planOfTaskRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("PlanOfTask not found")
        );

        if(!planOfTask.getTask().contains(task)) {

            planOfTask.getTask().add(task);
            PlanOfTask updated = planOfTaskRepository.save(planOfTask);
            return ResponseEntity.ok().body(planOfTaskResourceAssembler.toModel(updated));
        }
        return ResponseEntity.ok().body(planOfTaskResourceAssembler.toModel(planOfTask));
    }

    public ResponseEntity<PlanOfTaskResource> removeTaskInPlan(Long id, Long idTask) {


        return planOfTaskRepository.findById(id)
                .map(record -> {

                    Task Task = record.getTask().stream()
                            .filter(task -> task.getId().equals(idTask))
                            .toList().get(0);

                    record.getTask().remove(Task);

                    PlanOfTask updated = planOfTaskRepository.save(record);

                    return ResponseEntity.ok().body(planOfTaskResourceAssembler.toModel(updated));
                }).orElse(ResponseEntity.notFound().build());
    }
}
