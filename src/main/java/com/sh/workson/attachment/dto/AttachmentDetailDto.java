package com.sh.workson.attachment.dto;

import com.sh.workson.employee.entity.Employee;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class AttachmentDetailDto {
    private Long id;
    private String boardType;
    private Long boardId;
    private String originalFileName;
    private String key;
    private String url;
    private LocalDateTime createdAt;
    private Employee employee;
}
