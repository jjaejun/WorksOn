package com.sh.workson.project.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_project_comment_id_generator")
    @SequenceGenerator(
            name = "seq_project_comment_id_generator",
            sequenceName = "seq_project_comment_id"
    )
    private Long id;
    private String content;
    private Long parentCommentId;
    private int commentLevel;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JoinColumn(name = "emp_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Employee employee;

    private Long typeId;
    @Enumerated(EnumType.STRING)
    private ProjectCommentType type;


}
