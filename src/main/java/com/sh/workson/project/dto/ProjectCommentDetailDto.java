package com.sh.workson.project.dto;

import com.sh.workson.employee.entity.Employee;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectCommentDetailDto {
    private Long id;
    private String content;
    private Long parentCommentId;
    private int commentLevel;
    private LocalDateTime createdAt;
    private Employee employee;
    private Long typeId;
    private String type;
}
