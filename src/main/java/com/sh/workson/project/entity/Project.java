package com.sh.workson.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime endAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private List<ProjectEmployee> projectEmployees;
}
