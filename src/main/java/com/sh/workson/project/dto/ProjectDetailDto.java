package com.sh.workson.project.dto;

import com.sh.workson.employee.dto.EmployeeDetailDto;
import com.sh.workson.employee.dto.EmployeeSearchDto;
import com.sh.workson.project.entity.ProjectEmployee;
import com.sh.workson.project.entity.Status;
import com.sh.workson.project.entity.Task;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectDetailDto {
    private Long id;
    private String title;
    private LocalDate startAt;
    private LocalDate endAt;
    private String status;
    private EmployeeDetailDto employee;
    private List<TaskListDto> tasks = new ArrayList<>();

//    private List<EmployeeDetailDto> projectEmployees = new ArrayList<>();

}
