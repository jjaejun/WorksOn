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

    @Column(name = "approval_type_id")
    private Long approvalTypeId;

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

    // fk제약 조건 없이 필드에 선언
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "approval_type_id")
//    @Builder.Default
//    private List<ApprovalCooperation> approvalCooperations = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "approval_type_id")
//    @Builder.Default
//    private List<ApprovalEquipment> approvalEquipments = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "approval_type_id")
//    @Builder.Default
//    private List<ApprovalLeave> approvalLeaves = new ArrayList<>();



}

