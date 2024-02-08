package com.sh.workson.approval.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_approval_attachment_id_generator")
    @SequenceGenerator(
            name = "seq_approval_attachment_id_generator",
            sequenceName = "seq_approval_attachment_id"
    )
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false, name = "renamed_filename")
    private String renamedFilename;

    @Column(nullable = false, name = "original_filename")
    private String originalFilename;

    @Column(nullable = false)
    private String type;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_id")
    private Approval approval;


}
