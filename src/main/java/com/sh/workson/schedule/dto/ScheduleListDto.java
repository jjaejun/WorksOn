package com.sh.workson.schedule.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ScheduleListDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long scheduleCategoryId;
    private Long empId;

}
