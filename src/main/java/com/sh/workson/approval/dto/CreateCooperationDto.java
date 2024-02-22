package com.sh.workson.approval.dto;

import com.sh.workson.approval.entity.ApprovalLine;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCooperationDto {
    // approval 테이블로 전환
    private Long empId;
    private Date approvalEndDate;

    // approval_cooperation 테이블로 전환
    private String cooperationDept;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;

    // approval_line 테이블로 전환
    private String upfile;

    // approval_attachment 테이블로 전환
//    private Long approverId;
    private List<Long> approverId = new ArrayList<>();
//    List<ApprovalLine> approvalLineList = new ArrayList<>();


}
