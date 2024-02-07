package com.sh.workson.approval.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_approval_line_id_generator")
    @SequenceGenerator(
            name = "seq_approval_line_id_generator",
            sequenceName = "seq_approval_line_id"
    )
    private Long id;

    @Column(nullable = false)
    @JoinColumn(referencedColumnName = "id")
    private Long approver_id;


    private String rejection;

    private Timestamp confirm_date;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String approval_id;
}
