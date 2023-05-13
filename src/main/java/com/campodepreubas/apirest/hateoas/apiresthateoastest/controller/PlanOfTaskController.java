package com.campodepreubas.apirest.hateoas.apiresthateoastest.controller;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PlanOfTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.Task;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PlanOfTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PlanOfTaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.TaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.services.PlanOfTaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return planOfTaskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanOfTaskResource> getPlanOfUser(@PathVariable Long id){
        return planOfTaskService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PlanOfTaskResource>  postPlanOfUser(@RequestBody PlanOfTaskRequest planOfUser){
        return planOfTaskService.save(planOfUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanOfTaskResource>  putPlanOfUser(@PathVariable Long id, @RequestBody PlanOfTaskRequest planOfUser){
        return planOfTaskService.update(id,planOfUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanOfTaskResource>  deletePlanOfUser(@PathVariable Long id){
        return planOfTaskService.delete(id);
    }

    @PutMapping("/{id}/task/{idTask}")
    public ResponseEntity<PlanOfTaskResource>  addtaskInPlanOfUser(@PathVariable Long id, @PathVariable Long idTask){
        return planOfTaskService.addTaskInPlan(id,idTask);
    }
    @DeleteMapping("/{id}/task/{idTask}")
    public ResponseEntity<PlanOfTaskResource>  removetaskIntPlanOfUser(@PathVariable Long id, @PathVariable Long idTask){
        return planOfTaskService.removeTaskInPlan(id,idTask);
    }
}
