package com.campodepreubas.apirest.hateoas.apiresthateoastest.model;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private Double effort;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PlanOfTask> planOfTask;

    @ManyToOne
    private PeriodTask period;

}
