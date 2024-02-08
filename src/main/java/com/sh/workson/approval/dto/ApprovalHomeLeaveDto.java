package com.sh.workson.approval.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApprovalHomeLeaveDto {
    // approval
    private Long id;
    private String emergency;
    private Timestamp approvalEndDate;

    // approval Form
    private String name;
    private String title;

    // employee
    private String empId; // Employee#username이 들어올 예정

    // approval_attachment
    private int attachCount;



}
