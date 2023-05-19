package com.campodepreubas.apirest.hateoas.apiresthateoastest.controller;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PlanOfTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PlanOfTaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.TaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.services.PlanOfTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans-of-task")
public class PlanOfTaskController {

    @Autowired
    private PlanOfTaskService planOfTaskService;

    @GetMapping
    public ResponseEntity<List<PlanOfTaskResource>> getPlansOfUser(){

        return   ResponseEntity.ok(planOfTaskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanOfTaskResource> getPlanOfUser(@PathVariable Long id){
        return ResponseEntity.ok(planOfTaskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlanOfTaskResource>  postPlanOfUser(@RequestBody PlanOfTaskRequest planOfUser){
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(planOfTaskService.save(planOfUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanOfTaskResource>  putPlanOfUser(@PathVariable Long id, @RequestBody PlanOfTaskRequest planOfUser){
        return ResponseEntity.accepted().body(planOfTaskService.update(id,planOfUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  deletePlanOfUser(@PathVariable Long id){
        return ResponseEntity.accepted().body(planOfTaskService.delete(id));
    }

    @PutMapping("/{id}/task/{idTask}")
    public ResponseEntity<PlanOfTaskResource>  addtaskInPlanOfUser(@PathVariable Long id, @PathVariable Long idTask){
        return  ResponseEntity.accepted().body(planOfTaskService.addTaskInPlan(id,idTask));
    }
    @DeleteMapping("/{id}/task/{idTask}")
    public ResponseEntity<Boolean>  removetaskIntPlanOfUser(@PathVariable Long id, @PathVariable Long idTask){
        return ResponseEntity.accepted().body(planOfTaskService.removeTaskInPlan(id,idTask));
    }
    @PatchMapping("/{id}/task/{idTask}")
    public ResponseEntity<PlanOfTaskResource>  patchtaskIntPlanOfUser(@PathVariable Long id, @PathVariable Long idTask){
        return ResponseEntity.accepted().body(planOfTaskService.patchTaskInPlan(id,idTask));
    }
}
