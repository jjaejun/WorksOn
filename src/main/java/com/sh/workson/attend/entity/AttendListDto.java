package com.sh.workson.attend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendListDto {
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private State state;
    private Long employeeId;
    private String content;
}
