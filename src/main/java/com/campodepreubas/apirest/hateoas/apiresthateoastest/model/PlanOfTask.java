package com.campodepreubas.apirest.hateoas.apiresthateoastest.model;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums.StatusTaskEnum;
import lombok.*;
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
public @Data class PlanOfTask implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.ALWAYS)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private StatusTaskEnum status;

    @ManyToMany(mappedBy = "planOfTask",cascade = CascadeType.ALL)
    private List<Task> task;

    @OneToOne
    private User user;

    @Column
    private Timestamp startDate;
}
