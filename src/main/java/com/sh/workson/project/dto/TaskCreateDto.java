package com.sh.workson.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDto {
    private String name;
    private Long taskOwnerId;
    private Long taskEmpId;
    private int priority;
    private String status;
    private LocalDate startAt;
    private LocalDate endAt;
    private String content;
    private Long projectId;
}
