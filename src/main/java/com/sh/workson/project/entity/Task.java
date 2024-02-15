package com.sh.workson.project.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_task_id_generator")
    @SequenceGenerator(
            name = "seq_task_id_generator",
            sequenceName = "seq_task_id"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    private String content;
    private int priority;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @Column
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Employee owner;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;
}
