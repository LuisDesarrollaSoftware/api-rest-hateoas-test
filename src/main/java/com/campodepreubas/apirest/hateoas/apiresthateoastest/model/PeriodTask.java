package com.campodepreubas.apirest.hateoas.apiresthateoastest.model;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.PeriodicityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class PeriodTask implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.ALWAYS)
    private Long id;

    @Column
    private String name;

    @Column
    private PeriodicityEnum periodicity;



}
