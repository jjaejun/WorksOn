package com.sh.workson.department.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_department_id_generator")
    @SequenceGenerator(
            name = "seq_department_id_generator",
            sequenceName = "seq_department_id"
    )
    private Long id;
    @Column(nullable = false)
    private String name;


}
