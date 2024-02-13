package com.sh.workson.project.controller;

import com.sh.workson.attachment.dto.AttachmentCreateDto;
import com.sh.workson.attachment.entity.AttachType;
import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.project.dto.ProjectCreateDto;
import com.sh.workson.project.dto.ProjectDetailDto;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.ProjectEmployee;
import com.sh.workson.project.entity.Status;
import com.sh.workson.project.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/totalProjectList.do")
    public void totalProjectList(
            @PageableDefault(size = 5, page = 0)Pageable pageable,
            Model model
    ){
        Page<ProjectListDto> projects = projectService.findAll(pageable);
        log.debug("project = {}", projects.getContent());
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("totalCount", projects.getTotalElements());
    }

    @GetMapping("/projectList.do")
    public void projectList(
            @PageableDefault(size = 5, page = 0)Pageable pageable,
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
            Model model
    ){
        ProjectDetailDto projectDetailDto = projectService.findById(id);
        model.addAttribute("project", projectDetailDto);
        log.debug("project = {}", projectDetailDto);
    }


    @GetMapping("/projectEmployeeList.do")
    public ResponseEntity<?> projectEmployeeList(
            @RequestParam("projectId") Long id
    ){
        List<ProjectEmployee> employees = projectService.findAllProjectEmployeesByProjectID(id);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
