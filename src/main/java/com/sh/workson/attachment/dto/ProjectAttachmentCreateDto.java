package com.sh.workson.attachment.dto;

import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectAttachmentCreateDto {
    private Long boardId;
    private AttachType attachType;
    private String originalFilename;
    private String key;
    private String url;
    private LocalDateTime createdAt;
    private Employee employee;
}
