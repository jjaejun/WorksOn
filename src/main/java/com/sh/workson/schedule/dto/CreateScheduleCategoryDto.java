package com.sh.workson.schedule.dto;

import lombok.Data;

@Data
public class CreateScheduleCategoryDto {
    private String name;
    private String color;
    private Long empId;
}
