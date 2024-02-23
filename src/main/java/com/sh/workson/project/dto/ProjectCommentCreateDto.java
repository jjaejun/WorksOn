package com.sh.workson.project.dto;

import lombok.Data;

@Data
public class ProjectCommentCreateDto {
    private String content;
    private Long parentCommentId;
    private int commentLevel;
    private Long typeId;
    private Long empId;
    private String type;
}
