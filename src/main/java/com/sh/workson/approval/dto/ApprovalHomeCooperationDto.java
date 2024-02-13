package com.sh.workson.approval.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApprovalHomeCooperationDto {
    // approval
    private Long id;
    private String emergency;
    private Timestamp approvalEndDate;
    private String status;

    // approval Form
    private String name; // Approval_form#name
    private String title; // Approval_form#title

    // employee
    private String empId; // Employee#username이 들어올 예정
    private String empReId;

    // department
    private String deptId;

    // approval_attachment
    private int attachCount;
    private Long viewCount;



}
