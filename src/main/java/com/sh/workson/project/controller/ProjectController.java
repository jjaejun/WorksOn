package com.sh.workson.project.controller;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.dto.ProjectAttachmentCreateDto;
import com.sh.workson.attachment.dto.ProjectAttachmentDetailDto;
import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.service.AttachmentService;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.project.dto.*;
import com.sh.workson.project.entity.ProjectEmployee;
import com.sh.workson.project.entity.Task;
import com.sh.workson.project.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/project")
@Validated
public class ProjectController {


    @Autowired
    private ProjectService projectService;
    @Autowired
    private S3FileService s3FileService;
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/totalProjectList.do")
    public void totalProjectList(
            @PageableDefault(size = 8, page = 0)Pageable pageable,
            Model model
    ){
        Page<ProjectListDto> projects = projectService.findAll(pageable);
        log.debug("project = {}", projects.getContent());
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("totalCount", projects.getTotalElements());
    }

    @GetMapping("/projectList.do")
    public void projectList(
            @PageableDefault(size = 8, page = 0)Pageable pageable,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), pageable);
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), pageable);

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
    }

    @GetMapping("/createProject.do")
    public void createProject(){};

    @PostMapping("/createProject.do")
    public String createProject(
            ProjectCreateDto projectCreateDto,
            BindingResult bindingResult,
            @RequestParam(name = "files") List<MultipartFile> files,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        log.debug("files = {}", files);
        log.debug("projectCreateDto = {}", projectCreateDto);

        // 첨부파일 S3에 저장
        for(MultipartFile file : files) {
            log.debug("file = {}", file);
            if(file.getSize() > 0){
                AttachmentCreateDto attachmentCreateDto = s3FileService.upload(file, AttachType.PROJECT);
                attachmentCreateDto.setEmployee(employeeDetails.getEmployee());
                log.debug("attachmentCreateDto = {}", attachmentCreateDto);
                projectCreateDto.addAttachmentCreateDto(attachmentCreateDto);
            }
        }

        projectCreateDto.setEmployee(employeeDetails.getEmployee());
        projectService.createProject(projectCreateDto);

        return "redirect:/project/projectList.do";
    }

    @GetMapping("/projectDetail.do")
    public void projectDetail(
            @RequestParam("id") Long id,
            Model model,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ){
        ProjectDetailDto projectDetailDto = projectService.findById(id);
        model.addAttribute("project", projectDetailDto);

        List<TaskListDto> todos = new ArrayList<>();
        List<TaskListDto> progresses = new ArrayList<>();
        List<TaskListDto> dones = new ArrayList<>();
        // task 분류하기
        if(!projectDetailDto.getTasks().isEmpty()){
            for(TaskListDto task: projectDetailDto.getTasks()){
                switch (task.getStatus()){
                    case "TODO" : todos.add(task); break;
                    case "INPROGRESS" : progresses.add(task); break;
                    case "DONE" : dones.add(task); break;
                    default: throw new RuntimeException("[task 조회 오류 : 존재하지 않는 status]");
                }
            }
        }

        // 사원이 참여중인 프로젝트만 조회
        Page<ProjectListDto> projects = projectService.findByEmpId(employeeDetails.getEmployee(), pageable);
        // 사원이 생성한 프로젝트 조회
        Page<ProjectListDto> projects2 = projectService.findByOwnerId(employeeDetails.getEmployee(), pageable);

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projectEmp", projects.getContent());
        model.addAttribute("projectEmpTotalCount", projects.getTotalElements());
        model.addAttribute("projectOwner", projects2.getContent());
        model.addAttribute("projectOwnerTotalCount", projects2.getTotalElements());
        model.addAttribute("taskTodos", todos);
        model.addAttribute("taskProgresses", progresses);
        model.addAttribute("taskDone", dones);

        log.debug("project = {}", projectDetailDto);
    }


    @GetMapping("/projectEmployeeList.do")
    public ResponseEntity<?> projectEmployeeList(
            @RequestParam("projectId") Long id
    ){
        List<ProjectEmployee> employees = projectService.findAllProjectEmployeesByProjectID(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/projectAttachmentList.do")
    public ResponseEntity<?> projectAttachmentList(
            @RequestParam("projectId") Long id
    ){
        List<Attachment> attachments = projectService.findAllAttachmentByProjectId(id);
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }


    @GetMapping("/projectAttachDownload.do")
    public ResponseEntity<?> projectAttachDownload(
            @RequestParam("id") Long id,
            @RequestParam("url") String url
    ) throws UnsupportedEncodingException {

        ProjectAttachmentDetailDto attachmentDetailDto = attachmentService.findByProjectId(id);
        return s3FileService.download(attachmentDetailDto);
    }


    @PostMapping("/uploadAttachment.do")
    public ResponseEntity<?> uploadAttachment(
        @RequestParam("projectId") Long projectId,
        @RequestParam(name = "files") List<MultipartFile> files,
        @AuthenticationPrincipal EmployeeDetails employeeDetails,
        RedirectAttributes redirectAttributes
    ) throws IOException {

        List<ProjectAttachmentDetailDto> attachments = new ArrayList<>();
        // 첨부파일 S3에 저장
        for(MultipartFile file : files) {
            log.debug("file = {}", file);
            if(file.getSize() > 0){
                ProjectAttachmentCreateDto attachmentCreateDto = s3FileService.uploadProjectAttach(file, AttachType.PROJECT);
                attachmentCreateDto.setEmployee(employeeDetails.getEmployee());
                attachmentCreateDto.setBoardId(projectId);

                // DB에 저장하기
                attachments.add(attachmentService.createProjectAttachment(attachmentCreateDto));
                log.debug("attachments = {}", attachments);
            }
        }

        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @GetMapping("/dateTest.do")
    public void dateTest(){};

    @GetMapping("/searchDateTest.do")
    public void dateTest(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ){
        log.debug("startDate = {}", startDate);
        log.debug("endDate = {}", endDate);

        LocalDateTime startAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(startDate)), ZoneId.systemDefault());
        LocalDateTime endAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(endDate)), ZoneId.systemDefault());

        log.debug("T startDate = {}", startAt); // T startDate = 2024-02-07T00:00
        log.debug("T endDate = {}", endAt); // T endDate = 2024-02-10T00:00
    };

    @PostMapping("/createTask.do")
    public ResponseEntity<?> createTask(
        TaskCreateDto taskCreateDto,
        @AuthenticationPrincipal EmployeeDetails employeeDetails
    ){
        log.debug("taskCreateDto = {}", taskCreateDto);
        taskCreateDto.setTaskOwnerId(employeeDetails.getEmployee().getId());
        TaskListDto task = projectService.createTask(taskCreateDto);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/updateTask.do")
    public ResponseEntity<?> updateTask(
            TaskUpdateDto taskUpdateDto
    ){
        log.debug("taskUpdateDto = {}", taskUpdateDto);
        projectService.updateTask(taskUpdateDto);

        return null;
    }
}
