package com.sh.workson.schedule.dto;

import com.sh.workson.employee.entity.Employee;
import lombok.Data;
@Data
public class ScheduleCategoryDto {
    private Long id;
    private String name;
    private String color;
    private Long empId;
}
