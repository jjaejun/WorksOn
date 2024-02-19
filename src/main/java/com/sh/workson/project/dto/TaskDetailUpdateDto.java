package com.sh.workson.project.dto;

import com.sh.workson.project.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailUpdateDto {
    private Long id;
    private Long empId;
    private int priority;
    private String status;
    private LocalDate startAt;
    private LocalDate endAt;
    private String content;
}
