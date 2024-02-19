package com.sh.workson.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueCreateDto {
    private String name;
    private Long taskId;
    private Long empId;
    private Long ownerId;
    private int priority;
    private String status;
    private String content;
    private Long projectId;

}
