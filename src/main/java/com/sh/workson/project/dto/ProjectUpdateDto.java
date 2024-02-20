package com.sh.workson.project.dto;

import com.sh.workson.project.entity.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectUpdateDto {
    private Long id;
    private String title;
    private Status status;
    private LocalDate startAt;
    private LocalDate endAt;
}
