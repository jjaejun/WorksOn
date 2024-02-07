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

    @Column(nullable = false, name = "approver_id")
    private Long approverId;

    private String rejection;

    @Column(name = "confirm_date")
    private Timestamp confirmDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_id")
    private Approval approval;
}
