package com.sh.workson.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectCreateDto {
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
