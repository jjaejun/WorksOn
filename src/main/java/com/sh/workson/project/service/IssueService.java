package com.sh.workson.project.service;

import com.sh.workson.employee.dto.EmployeeTaskDetailDto;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.*;
import com.sh.workson.project.entity.Issue;
import com.sh.workson.project.entity.IssueStatus;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.Task;
import com.sh.workson.project.repository.IssueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<IssueDetailDto> findAllMyIssue(Long id, Pageable pageable) {
        Page<Issue> issues = issueRepository.findAllMyIssue(id, pageable);
        return issues.map(issue -> convertToIssueDetailDto(issue));
    }

    private IssueDetailDto convertToIssueDetailDto(Issue issue) {
        IssueDetailDto issueDetailDto = modelMapper.map(issue, IssueDetailDto.class);
        issueDetailDto.setEmployee(EmployeeTaskDetailDto.builder()
                .id(issue.getEmployee().getId())
                .profileUrl(issue.getEmployee().getProfileUrl())
                .name(issue.getEmployee().getName())
                .positionName(issue.getEmployee().getPosition().getName())
                .deptName(issue.getEmployee().getDepartment().getName())
                .build());
        issueDetailDto.setOwner(EmployeeTaskDetailDto.builder()
                .id(issue.getOwner().getId())
                .profileUrl(issue.getOwner().getProfileUrl())
                .name(issue.getOwner().getName())
                .positionName(issue.getOwner().getPosition().getName())
                .deptName(issue.getOwner().getDepartment().getName())
                .build());
        issueDetailDto.setProject(ProjectTaskDetailDto.builder()
                .id(issue.getProject().getId())
                .title(issue.getProject().getTitle())
                .build());
        if(issue.getTask() != null){
            issueDetailDto.setTask(TaskIssueDetailDto.builder()
                    .id(issue.getTask().getId())
                    .name(issue.getTask().getName())
                    .build());
        }
        return issueDetailDto;
    }

    public Page<IssueDetailDto> findTop3Issue(Long id, Pageable pageable) {
        Page<Issue> issues = issueRepository.findTop3Issue(id, pageable);
        return issues.map(issue -> convertToIssueDetailDto(issue));
    }

    public Issue createIssue(IssueCreateDto issueCreateDto) {
        Issue issue = issueRepository.save(issueCreateDtoConvertToIssue(issueCreateDto));
        return issue;
    }

    private Issue issueCreateDtoConvertToIssue(IssueCreateDto issueCreateDto) {
        Issue issue = new Issue();
        if(issueCreateDto.getTaskId() != null){
            issue = Issue.builder()
                    .content(issueCreateDto.getContent())
                    .owner(Employee.builder().id(issueCreateDto.getOwnerId()).build())
                    .task(Task.builder().id(issueCreateDto.getTaskId()).build())
                    .employee(Employee.builder().id(issueCreateDto.getEmpId()).build())
                    .priority(issueCreateDto.getPriority())
                    .status(IssueStatus.valueOf(issueCreateDto.getStatus()))
                    .name(issueCreateDto.getName())
                    .project(Project.builder().id(issueCreateDto.getProjectId()).build())
                    .build();
        }
        else {
            issue = Issue.builder()
                    .content(issueCreateDto.getContent())
                    .owner(Employee.builder().id(issueCreateDto.getOwnerId()).build())
                    .employee(Employee.builder().id(issueCreateDto.getEmpId()).build())
                    .priority(issueCreateDto.getPriority())
                    .status(IssueStatus.valueOf(issueCreateDto.getStatus()))
                    .name(issueCreateDto.getName())
                    .project(Project.builder().id(issueCreateDto.getProjectId()).build())
                    .build();
        }
        return issue;
    }
}
