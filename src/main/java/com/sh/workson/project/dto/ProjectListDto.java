package com.sh.workson.project.dto;

import com.sh.workson.employee.dto.EmployeeProjectOwnerDto;
import com.sh.workson.project.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProjectListDto {
    private Long id;
    private String title;
    private LocalDate startAt;
    private LocalDate endAt;
    private LocalDateTime updatedAt;
    private String status;

    private EmployeeProjectOwnerDto employee;
}
