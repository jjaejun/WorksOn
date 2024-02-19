package com.sh.workson.schedule.dto;

import lombok.Data;

@Data
public class UpdateSchduleCategoryDto {
    private Long id;
    private String name;
    private String color;
    private Long empId;
}
