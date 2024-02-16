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

    @Column(nullable = false, name = "cooperation_dept")
    private String cooperationDept;

    @Column(nullable = false, name = "start_date")
    private Timestamp StartDate;

    @Column(name = "end_date")
    private Timestamp EndDate;

    @Column(nullable = false)
    private Long people;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

//    @OneToMany(mappedBy = "approvalCooperation", fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Approval> approvals = new ArrayList<>();


}
