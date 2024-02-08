package com.sh.workson.project.service;

import com.sh.workson.employee.dto.EmployeeProjectOwnerDto;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProjectListDto> findAll(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.map(project -> convertToProjectDto(project));
    }


    public Page<ProjectListDto> findByEmpId(Employee employee, Pageable pageable) {
        Page<Project> projects = projectRepository.findByEmpId(employee.getId(), pageable);
        return projects.map(project -> convertToProjectDto(project));
    }

    private ProjectListDto convertToProjectDto(Project project) {
        ProjectListDto projectListDto = modelMapper.map(project, ProjectListDto.class);

        EmployeeProjectOwnerDto employeeProjectOwnerDto = EmployeeProjectOwnerDto.builder()
                .id(project.getEmployee().getId())
                .name(project.getEmployee().getName())
                .deptName(project.getEmployee().getDepartment().getName())
                .positionName(project.getEmployee().getPosition().getName())
                .profileUrl(project.getEmployee().getProfileUrl())
                .build();
        projectListDto.setEmployee(employeeProjectOwnerDto);

        return projectListDto;
    }

}
