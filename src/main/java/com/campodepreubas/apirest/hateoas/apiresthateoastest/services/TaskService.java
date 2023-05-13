package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.TaskAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.TaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
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

    public ResponseEntity<List<TaskResource>>  findAll() {
        return ResponseEntity.ok(taskRepository.findAll().stream().map(taskResourceAssembler::toModel).toList());
    }

    public ResponseEntity<TaskResource> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskResourceAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new RuntimeException("Task not found"));

    }

    public ResponseEntity<TaskResource> save(TaskRequest taskRequest) {

        Task task =  Task.builder()
                .name(taskRequest.getName())
                .effort(taskRequest.getEffort())
                .build();

        Task saved = taskRepository.save(task);

        TaskResource resource = taskResourceAssembler.toModel(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    public ResponseEntity<TaskResource> update(Long id, TaskRequest taskRequest) {

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

        return ResponseEntity.accepted().body(resource);
    }

    public ResponseEntity<Void> delete(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        return ResponseEntity.accepted().build();
    }
}
