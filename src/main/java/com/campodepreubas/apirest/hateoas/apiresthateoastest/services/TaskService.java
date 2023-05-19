package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.TaskAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PeriodTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.TaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PeriodTaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.TaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskAssembler taskResourceAssembler;

    @Autowired
    private PeriodService periodTaskService;

    public List<TaskResource>  findAll() {
        return taskRepository.findAll().stream().map(taskResourceAssembler::toModel).toList();
    }

    public TaskResource findById(Long id) {
        return taskRepository.findById(id)
                .map(taskResourceAssembler::toModel)
                .orElseThrow(()-> new RuntimeException("Task not found"));

    }

    public TaskResource save(TaskRequest taskRequest) {

        Task task =  Task.builder()
                .name(taskRequest.getName())
                .effort(taskRequest.getEffort())
                .build();

        Task saved = taskRepository.save(task);

        TaskResource resource = taskResourceAssembler.toModel(saved);

        return resource;
    }

    public TaskResource update(Long id, TaskRequest taskRequest) {

        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        Task task = Task.builder()
                .id(id)
                .name(taskRequest.getName())
                .effort(taskRequest.getEffort())
                .build();

        Task updated = taskRepository.save(task);

        TaskResource resource = taskResourceAssembler.toModel(updated);

        return resource;
    }

    public Boolean delete(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        return taskRepository.findById(id).map(
                task -> {
                    taskRepository.delete(task);
                    return true;
                }).orElse(false);

    }

    public TaskResource patchPeriod(Long id, Integer period) {

        Task task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );

        ;

        PeriodTaskResource periodTaskResource = periodTaskService.findById(period);

        PeriodTask periodTask = PeriodTask.builder()
                .name(periodTaskResource.getName())
                .periodicity(periodTaskResource.getPeriodicity())
                .build();

        task.setPeriod(periodTask);

        Task updated = taskRepository.save(task);

        TaskResource resource = taskResourceAssembler.toModel(updated);

        return resource;
    }
}
