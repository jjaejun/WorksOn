package com.sh.workson.approval.dto;


import java.sql.Timestamp;

public interface IApprovalLeave {
    Long getId(); // approval#Id
    Timestamp getApprovalEndDate(); // approval#기안일
    String getEmpId(); // employee# 사원 이름
    String getDeptId(); // department# 해당 사원 부서
    Long getLeId(); //  approval_leave# 문서 번호
    Timestamp getStartDate(); // approval_leave# 연차 시작일
    Timestamp getEndDate(); // approval_leave# 연차 종료일
    String getLeaveContent(); // approval_leave# 연차 내용

    String getLeaveType(); // approval_leave# 연차 종류
    String getAnnul(); // approval_leave# 연차 반차 여부 y, n y = 연차, n = 반차
    Long getLeaveCount();


}
