package com.sh.workson.project.controller;

import com.sh.workson.auth.vo.EmployeeDetails;
import com.sh.workson.project.dto.ProjectListDto;
import com.sh.workson.project.entity.Project;
import com.sh.workson.project.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/project")
@Validated
public class ProjectController {


    @Autowired
    private ProjectService projectService;

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
        log.debug("project = {}", projects.getContent());
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("totalCount", projects.getTotalElements());
    }
}
