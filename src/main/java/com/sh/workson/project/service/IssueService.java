package com.sh.workson.project.service;

import com.sh.workson.employee.dto.EmployeeTaskDetailDto;
import com.sh.workson.project.dto.IssueDetailDto;
import com.sh.workson.project.dto.ProjectTaskDetailDto;
import com.sh.workson.project.dto.TaskDetailDto;
import com.sh.workson.project.dto.TaskIssueDetailDto;
import com.sh.workson.project.entity.Issue;
import com.sh.workson.project.entity.Task;
import com.sh.workson.project.repository.IssueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                    .id(issue.getProject().getId())
                    .name(issue.getProject().getTitle())
                    .build());
        }
        return issueDetailDto;
    }
}
