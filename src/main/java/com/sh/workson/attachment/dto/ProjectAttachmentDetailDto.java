package com.sh.workson.attachment.dto;

import com.sh.workson.employee.entity.Employee;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectAttachmentDetailDto {
    private Long id;
    private Long boardId;
    private String originalFilename;
    private String key;
    private String url;
    private LocalDateTime createdAt;
    private String empName;
    private String empDeptName;
    private String empPositionName;
}
