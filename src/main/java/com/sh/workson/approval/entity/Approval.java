package com.sh.workson.approval.entity;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_approval_id_generator")
    @SequenceGenerator(
            name = "seq_approval_id_generator",
            sequenceName = "seq_approval_id"
    )
    private Long id;

    @JoinColumn(referencedColumnName = "id")
    private Long approval_type_id;

    @CreationTimestamp
    private Timestamp approval_start_date;

    private Timestamp approval_end_date;

    @Column
    @Enumerated(EnumType.STRING)
    private Emergency emergency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "emp_id")
    private Long empId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_id")
    @Builder.Default
    private List<ApprovalAttachment> approvalAttachments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_id")
    @Builder.Default
    private List<ApprovalLine> approvallines = new ArrayList<>();


}
