package com.sh.workson.project.service;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.repository.AttachmentRepository;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.employee.dto.EmployeeProjectOwnerDto;
import com.sh.workson.employee.entity.Employee;
import com.sh.workson.project.dto.ProjectCreateDto;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.Status;
import com.sh.workson.project.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private S3FileService s3FileService;
    @Autowired
    private AttachmentRepository attachmentRepository;

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

    /**
     * mapper로 projectCreateDto -> project로 변환
     * s3service한테 파일 저장 요청
     * @param projectCreateDto
     */
    public void createProject(ProjectCreateDto projectCreateDto) {
        Project project = modelMapper.map(projectCreateDto, Project.class);
        log.debug("project = {}", project);

        // mapper가 처리하지 못하는 startAt, endAt, status, emplist 처리하기
        // status
        project.setStatus(Status.NOT_YET);
        // startAt, endAt
        project.setStartAt(LocalDate.parse(projectCreateDto.getStartAt()));
        project.setEndAt(LocalDate.parse(projectCreateDto.getEndAt()));
        // emplist 처리

        projectRepository.save(project);

        // attach의 projectId(게시판이라면 boardId) 셋팅 및 attachment 타입으로 변환
        if(!projectCreateDto.getAttaches().isEmpty()) {
            for (AttachmentCreateDto attach: projectCreateDto.getAttaches()) {
                attach.setBoardId(project.getId());
                Attachment attachment = modelMapper.map(attach, Attachment.class);
                log.debug("attachment = {}", attachment);
                attachmentRepository.save(attachment);
            }
        }

    }

    public Page<ProjectListDto> findByOwnerId(Employee employee, Pageable pageable) {
        Page<Project> projects = projectRepository.findByOwnerId(employee.getId(), pageable);
        return projects.map(project -> convertToProjectDto(project));
    }
}
