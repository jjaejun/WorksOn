package com.sh.workson.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeProjectOwnerDto {
    private Long id;
    private String name;
    private String profileUrl;
    private String deptName;
    private String positionName;
}
