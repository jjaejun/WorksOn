package com.sh.workson.dailywork.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DailyWorkListDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private int cherryCount;
    private Long employeeId;
    private int seed;
    private int cherry;
}
