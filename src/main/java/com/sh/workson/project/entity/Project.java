package com.sh.workson.project.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_project_id_generator")
    @SequenceGenerator(
            name = "seq_project_id_generator",
            sequenceName = "seq_project_id"
    )
    private Long id;
    @Column(nullable = false)
    private String title;
    private LocalDate startAt;
    private LocalDate endAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Employee employee;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @Builder.Default
    private List<ProjectEmployee> projectEmployees = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

}
