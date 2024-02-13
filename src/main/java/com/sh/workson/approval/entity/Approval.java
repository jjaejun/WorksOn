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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @CreationTimestamp
    @Column(name = "approval_start_date")
    private Timestamp approvalStartDate;

    @Column(name = "approval_end_date")
    private Timestamp approvalEndDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Emergency emergency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @OneToMany(mappedBy = "approval", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ApprovalAttachment> approvalAttachments = new ArrayList<>();

    @OneToMany(mappedBy = "approval", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ApprovalLine> approvalLines = new ArrayList<>();

    // 브릿지 테이블이랑 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_leave_id") // approval_form. approval_leave_id 컬럼 참조
    private ApprovalLeave approvalLeave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_equipment_id") // approval_form. approval_equipment_id 컬럼 참조
    private ApprovalEquipment approvalEquipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_cooperation_id") // approval_form. approval_cooperation_id 컬럼 참조
    private ApprovalCooperation approvalCooperation;





}

