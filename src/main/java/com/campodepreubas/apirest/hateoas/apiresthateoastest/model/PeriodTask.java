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
import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class PeriodTask implements Serializable {

    @Column
    private String name;
    @Id
    @Column(name = "periodicity", nullable = false)
    private Integer periodicity;



}
