package com.sh.workson.project.controller;

import com.sh.workson.attachment.entity.Attachment;
import com.sh.workson.attachment.service.S3FileService;
import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.project.dto.ProjectCreateDto;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.entity.Status;
import com.sh.workson.project.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        List<ProjectListDto> projectsIng = new ArrayList<>();
        List<ProjectListDto> projectsDone = new ArrayList<>();

        // ing랑 done 구분하기
        for (ProjectListDto project: projects) {
            if(project.getStatus().equals(Status.DONE.toString())){
                projectsDone.add(project);
            }
            else if(project.getStatus().equals(Status.ING.toString())){
                projectsIng.add(project);
            }
        }

        log.debug("project = {}", projects.getContent());
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("projectsIng", projectsIng);
        model.addAttribute("projectsDone", projectsDone);
        model.addAttribute("totalCount", projects.getTotalElements());
    }

    @PostMapping("/createProject.do")
    public ResponseEntity<?> createProject(
            ProjectCreateDto projectCreateDto,
            BindingResult bindingResult,
            @RequestParam(name = "files") List<MultipartFile> files,
            @AuthenticationPrincipal EmployeeDetails employeeDetails,
            RedirectAttributes redirectAttributes
    ){
        log.debug("files = {}", files);
        List<Attachment> attachments = new ArrayList<>();

//        // 첨부파일 S3에 저장
//        for(MultipartFile file : files) {
//            if(file.getSize() > 0){
//                com.sh.app.attachment.dto.AttachmentCreateDto attachmentCreateDto = s3FileService.upload(file);
//                log.debug("attachmentCreateDto = {}", attachmentCreateDto);
//                boardCreateDto.addAttachmentCreateDto(attachmentCreateDto);
//            }
//        }

        return null;
    }
}
