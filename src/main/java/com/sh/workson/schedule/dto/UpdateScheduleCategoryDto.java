package com.sh.workson.schedule.dto;

import lombok.Data;

@Data
public class UpdateScheduleCategoryDto {
    private Long id;
    private String name;
    private String color;
    private Long empId;
}
