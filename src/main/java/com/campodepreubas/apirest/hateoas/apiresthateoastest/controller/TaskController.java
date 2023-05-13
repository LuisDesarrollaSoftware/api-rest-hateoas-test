package com.campodepreubas.apirest.hateoas.apiresthateoastest.controller;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.TaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.TaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResource>>  getTasks() {
        return taskService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResource> getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }
    @PostMapping
    public ResponseEntity<TaskResource>  postTask(@RequestBody TaskRequest task) {
        return taskService.save(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskResource>  putTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id,taskRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteTask(Long id) {
        return taskService.delete(id);
    }
}
