package com.sh.workson.approval.dto;

public interface IApproverCooperation {

    Long getLineId(); // approval_line# id
    Long getApproverId(); // approval_line# approverid

    String getSign(); // approval_line# sign

    String getApproverName(); // employee# name
}
