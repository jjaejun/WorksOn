package com.sh.workson.employee.dto;

import lombok.Data;

@Data
public class EmployeeUpdateProfileDto {
    private Long id;
    private String profileOriginalName;
    private String profileKey;
    private String profileUrl;
}
