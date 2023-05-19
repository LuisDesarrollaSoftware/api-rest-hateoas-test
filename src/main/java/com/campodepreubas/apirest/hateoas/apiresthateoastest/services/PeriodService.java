package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.PeriodTaskAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.PeriodTaskResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.PeriodTask;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.PeriodTaskRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PeriodTaskAssembler assembler;

    public List<PeriodTaskResource> findAll() {
        return periodRepository.findAll().stream().map(assembler::toModel).toList();
    }

    public PeriodTaskResource findById(Integer id) {
        return periodRepository.findById(id)
                .map(assembler::toModel).orElseThrow(
                        () -> new RuntimeException("Period not found")
                );
    }

    public PeriodTaskResource save(PeriodTaskRequest periodRequest) {
        PeriodTask period = PeriodTask.builder()
                .name(periodRequest.getName())
                .periodicity(periodRequest.getPeriodicity())
                .build();

        return assembler.toModel(periodRepository.save(period));
    }

    public PeriodTaskResource update(Integer id, PeriodTaskRequest periodRequest) {
        return periodRepository.findById(id)
                .map(record -> {
                    record.setName(periodRequest.getName());
                    record.setPeriodicity(periodRequest.getPeriodicity());
                    PeriodTask updated = periodRepository.save(record);
                    return assembler.toModel(updated);
                }).orElseThrow(() -> new RuntimeException("Period not found") {
                });
    }

    public Boolean delete(Integer id) {
        return periodRepository.findById(id)
                .map(record -> {
                    periodRepository.deleteById(id);
                    return true;
                }).orElse(false);

    }
}
