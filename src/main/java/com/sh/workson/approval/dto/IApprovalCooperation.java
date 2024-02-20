package com.sh.workson.approval.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public interface IApprovalCooperation {
    Long getId(); // approval# id
    Timestamp getApprovalEndDate(); // approval# 기안일
    String getEmpId(); // employee# 사원 이름
    String getDeptId(); // department# 해당 사원 부서
    Long getCoId(); // approval_cooperation# 문서번호
    String getCooperationDept(); // approval_cooperation# 협조 부서
    String getTitle(); // approval_cooperation# 제목
    String getContent(); // approval_cooperation# 내용
    Timestamp getStartDate(); // approval_cooperation# 협조 시작일
    Timestamp getEndDate(); // approval_cooperation# 협조 종료일


}
