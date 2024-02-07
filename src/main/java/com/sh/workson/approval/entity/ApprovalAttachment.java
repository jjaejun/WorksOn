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

    @Column(nullable = false)
    private String renamed_filename;

    @Column(nullable = false)
    private String original_filename;

    @Column(nullable = false)
    private String type;

    @CreationTimestamp
    private Timestamp created_at;

    @Column(nullable = false)
    private Long approval_id;
}
