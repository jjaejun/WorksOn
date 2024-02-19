package com.sh.workson.project.dto;

import com.sh.workson.employee.dto.EmployeeTaskDetailDto;
import com.sh.workson.project.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDetailDto {
    private Long id;
    private String name;
    private String content;
    private int priority;
    private LocalDate startAt;
    private LocalDate endAt;
    private String status;

    private EmployeeTaskDetailDto owner;
    private EmployeeTaskDetailDto employee;
    private ProjectTaskDetailDto project;

}
