package com.campodepreubas.apirest.hateoas.apiresthateoastest.model;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_app")
public @Data class User implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.ALWAYS)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PlanOfTask planOftask;
}
