package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.PeriodTaskAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PeriodTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PeriodTaskAssembler assembler;

    public ResponseEntity<List<PeriodTaskResource>> findAll() {
        return ResponseEntity.ok(periodRepository.findAll().stream().map(assembler::toModel).toList());
    }

    public ResponseEntity<PeriodTaskResource> findById(Long id) {
        return periodRepository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<PeriodTaskResource> save(PeriodTask period) {
        return ResponseEntity.ok().body(assembler.toModel(periodRepository.save(period)));
    }

    public ResponseEntity<PeriodTaskResource> update(Long id, PeriodTask period) {
        return periodRepository.findById(id)
                .map(record -> {
                    record.setName(period.getName());
                    record.setPeriodicity(period.getPeriodicity());
                    PeriodTask updated = periodRepository.save(record);
                    return ResponseEntity.ok().body(assembler.toModel(updated));
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<PeriodTaskResource> delete(Long id) {
        return periodRepository.findById(id)
                .map(record -> {
                    periodRepository.deleteById(id);
                    return ResponseEntity.ok().body(assembler.toModel(record));
                }).orElse(ResponseEntity.notFound().build());
    }
}
