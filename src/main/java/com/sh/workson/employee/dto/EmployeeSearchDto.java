package com.sh.workson.employee.dto;

import com.sh.workson.department.entity.Department;
import com.sh.workson.position.entity.Position;
import lombok.Data;

@Data
public class EmployeeSearchDto {
    private Long id;
    private String name;
    private String profileUrl;
    private Department department;
    private Position position;
}
