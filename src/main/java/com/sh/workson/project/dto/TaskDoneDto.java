package com.sh.workson.project.dto;


import lombok.Data;

@Data
public class TaskDoneDto {
    private Long id;
    private String name;
    private int priority;
    private String status;

    private Long empId;
    private String empName;
    private String empProfileUrl;
    private String positionName;
    private String deptName;

}
