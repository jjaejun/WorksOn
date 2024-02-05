package com.sh.workson.project.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_project_employee_id_generator")
    @SequenceGenerator(
            name = "seq_project_employee_id_generator",
            sequenceName = "seq_project_employee_id"
    )
    private Long id;
    @Column(nullable = false, name = "project_id")
    private Long projectId;
    @Column(nullable = false, name = "project_role")
    @Enumerated(EnumType.STRING)
    private ProjectRole role;

    // 참조자 목록
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", unique = false)
    private Employee employee;
}
