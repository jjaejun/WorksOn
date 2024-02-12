package com.sh.workson.employee.dto;

import com.sh.workson.department.entity.Department;
import com.sh.workson.position.entity.Position;
import lombok.Data;

@Data
public class EmployeeDetailDto {
    private Long id;
    private String email;
    private String name;
    private Department department;
    private Position position;
}
