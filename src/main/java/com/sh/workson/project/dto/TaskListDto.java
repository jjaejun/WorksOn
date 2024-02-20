package com.sh.workson.project.dto;


import com.sh.workson.project.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDto {
    private Long id;
    private String name;
    private int priority;
    private String status;

    private Long empId;
    private String empName;
    private String empProfileUrl;
    private String positionName;
}
