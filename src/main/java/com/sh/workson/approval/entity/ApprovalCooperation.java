package com.sh.workson.approval.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalCooperation {
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

    private String content;

    @Column(nullable = false)
    @JoinColumn(referencedColumnName = "id")
    private String cooperation_dept;

    @Column(nullable = false)
    private Timestamp cooperation_start_date;

    private Timestamp cooperation_end_date;

    @Column(nullable = false)
    private Long people;

    @CreationTimestamp
    private Timestamp created_at;



}
