package com.sh.workson.position.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_department_id_generator")
    @SequenceGenerator(
            name = "seq_department_id_generator",
            sequenceName = "seq_department_id"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private int positionPower;
}
