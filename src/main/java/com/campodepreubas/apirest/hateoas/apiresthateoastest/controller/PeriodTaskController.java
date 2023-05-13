package com.campodepreubas.apirest.hateoas.apiresthateoastest.controller;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PeriodTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return periodService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PeriodTaskResource> getPeriod(Long id) {
        return periodService.findById(id);
    }
    @PostMapping
    public ResponseEntity<PeriodTaskResource>  postPeriod(@RequestBody PeriodTask period) {
        return periodService.save(period);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PeriodTaskResource>  putPeriod(@PathVariable Long id, @RequestBody PeriodTask period) {
        return periodService.update(id,period);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PeriodTaskResource>  deletePeriod(Long id) {
        return periodService.delete(id);
    }


}
