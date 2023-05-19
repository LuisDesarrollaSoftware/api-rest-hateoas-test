package com.campodepreubas.apirest.hateoas.apiresthateoastest.controller;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PeriodTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PeriodTaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/periods")
public class PeriodTaskController {

    @Autowired
    private PeriodService periodService;

    @GetMapping
    public ResponseEntity<List<PeriodTaskResource>> getPeriods() {

        return ResponseEntity.ok(periodService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PeriodTaskResource> getPeriod(Integer id) {
        return ResponseEntity.ok(periodService.findById(id));
    }
    @PostMapping
    public ResponseEntity<PeriodTaskResource>  postPeriod(@RequestBody PeriodTaskRequest period) {
        return ResponseEntity.status(HttpStatus.CREATED).body(periodService.save(period));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PeriodTaskResource>  putPeriod(@PathVariable Integer id, @RequestBody PeriodTaskRequest period) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(periodService.update(id,period));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  deletePeriod(Integer id) {
        return ResponseEntity.accepted().body(periodService.delete(id)) ;
    }


}
