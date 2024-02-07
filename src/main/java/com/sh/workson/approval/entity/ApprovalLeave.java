package com.sh.workson.approval.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_approval_form_id_generator")
    @SequenceGenerator(
            name = "seq_approval_form_id_generator",
            sequenceName = "seq_approval_form_id"
    )
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Timestamp start_date;

    @Column(nullable = false)
    private Timestamp end_date;

    private String leave_content;

    @CreationTimestamp
    private Timestamp created_at;

}
