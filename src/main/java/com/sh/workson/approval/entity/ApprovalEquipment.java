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
public class ApprovalEquipment {
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

    @Column(nullable = false, name = "product_name")
    private String productName;

    private String usage;

    @Column(nullable = false)
    private Long price;

    private Long count;

    @CreationTimestamp
    @Column(nullable = false, name = "equipment_date")
    private Timestamp equipmentDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Approval approval;
}
