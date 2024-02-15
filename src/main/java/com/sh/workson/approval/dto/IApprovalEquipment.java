package com.sh.workson.approval.dto;

import java.sql.Timestamp;

public interface IApprovalEquipment {
    Long getId(); // approval# id
    Timestamp getApprovalEndDate(); // approval# 기안일
    String getEmpId(); // employee# 사원 이름
    String getDeptId(); //department# 해당 사원 부서
    Long getEqId(); // approval_equipment# 문서번호

    String getTitle(); // approval_equipment# 제목
    String getContent(); // approval_equipment# 내용
    String getProductName(); // approval_equipment# 상품명
    String getPrice(); // approval_equipment# 가격
    Long getCount(); // approval_equipment# 수량
    Long getTotalPrice(); // approval_equipment# count * price
    String getUsage(); // approval_equipment# 용도







}
